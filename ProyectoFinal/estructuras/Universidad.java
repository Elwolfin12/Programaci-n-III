package estructuras;

import excepciones.Excepciones;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;
import modelo.*;

public class Universidad {

    private HashMap<String, Estudiante> estudiantes = new HashMap<>();
    private LinkedList<Profesor> profesores = new LinkedList<>();
    private LinkedList<Materia> materias = new LinkedList<>();
    private TreeMap<String, Aula> aulas = new TreeMap<>();
    private Facultad[] facultades = new Facultad[5];

    private String[] edificios;
    private int[][] distancias;

    private Queue<String> solicitudesBatch = new LinkedList<>();
    private Stack<String> reportes = new Stack<>();

    // Una sola pareja de pilas controla el deshacer y rehacer de todo el sistema.
    private Stack<Accion> pilaDeshacer = new Stack<>();
    private Stack<Accion> pilaRehacer = new Stack<>();

    // Facultad integrada en esta clase para mantener el proyecto sencillo. 
    public static class Facultad {
        private String nombre;

        public Facultad(String nombre) {
            this.nombre = nombre;
        }

        @Override
        public String toString() {
            return nombre;
        }
    }

    // Guarda lo necesario para deshacer o repetir una operación. 
    private static class Accion {
        String nombre;
        Runnable deshacer;
        Runnable rehacer;

        Accion(String nombre, Runnable deshacer, Runnable rehacer) {
            this.nombre = nombre;
            this.deshacer = deshacer;
            this.rehacer = rehacer;
        }
    }

    // Copia temporal de los inscritos y la cola de una materia. 
    private static class EstadoMateria {
        Materia materia;
        LinkedList<String> inscritos;
        LinkedList<String> espera;

        EstadoMateria(Materia materia) {
            this.materia = materia;
            inscritos = new LinkedList<>(materia.getInscritos());
            espera = new LinkedList<>(materia.getColaEspera());
        }
    }

    private void guardarAccion(String nombre, Runnable deshacer, Runnable rehacer) {
        pilaDeshacer.push(new Accion(nombre, deshacer, rehacer));
        pilaRehacer.clear();
    }

    public void deshacer() throws Excepciones.PilaDeshacerVaciaException {
        if (pilaDeshacer.empty()) {
            throw new Excepciones.PilaDeshacerVaciaException("No hay operaciones para deshacer.");
        }

        Accion accion = pilaDeshacer.pop();
        accion.deshacer.run();
        pilaRehacer.push(accion);
        System.out.println("Operación deshecha: " + accion.nombre);
    }

    public void rehacer() {
        if (pilaRehacer.empty()) {
            System.out.println("No hay operaciones para rehacer.");
            return;
        }

        Accion accion = pilaRehacer.pop();
        accion.rehacer.run();
        pilaDeshacer.push(accion);
        System.out.println("Operación rehecha: " + accion.nombre);
    }

    // -------------------- ESTUDIANTES --------------------

    public boolean registrarEstudiante(Estudiante estudiante) {
        if (estudiante == null || estudiantes.containsKey(estudiante.getId())) {
            return false;
        }

        estudiantes.put(estudiante.getId(), estudiante);

        guardarAccion(
            "registrar estudiante",
            () -> estudiantes.remove(estudiante.getId()),
            () -> estudiantes.put(estudiante.getId(), estudiante)
        );
        return true;
    }

    public Estudiante buscarEstudiante(String id) {
        return estudiantes.get(id);
    }

    public HashMap<String, Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public boolean eliminarEstudiante(String id) {
        Estudiante estudiante = estudiantes.get(id);
        if (estudiante == null) {
            return false;
        }

        // Guardar el estado de todas las materias antes de eliminar al estudiante.
        LinkedList<EstadoMateria> estadosAntes = guardarEstadosMaterias();

        estudiantes.remove(id);

        for (Materia materia : materias) {
            materia.getInscritos().remove(id);
            materia.getColaEspera().remove(id);
            asignarSiguienteEnEspera(materia);
        }

        LinkedList<EstadoMateria> estadosDespues = guardarEstadosMaterias();

        guardarAccion(
            "eliminar estudiante",
            () -> {
                estudiantes.put(id, estudiante);
                restaurarEstadosMaterias(estadosAntes);
            },
            () -> {
                estudiantes.remove(id);
                restaurarEstadosMaterias(estadosDespues);
            }
        );

        return true;
    }

    private LinkedList<EstadoMateria> guardarEstadosMaterias() {
        LinkedList<EstadoMateria> estados = new LinkedList<>();
        for (Materia materia : materias) {
            estados.add(new EstadoMateria(materia));
        }
        return estados;
    }

    private void restaurarEstadosMaterias(LinkedList<EstadoMateria> estados) {
        for (EstadoMateria estado : estados) {
            estado.materia.getInscritos().clear();
            estado.materia.getInscritos().addAll(estado.inscritos);

            estado.materia.getColaEspera().clear();
            estado.materia.getColaEspera().addAll(estado.espera);
        }
    }

    // -------------------- MATERIAS E INSCRIPCIONES --------------------

    public void agregarMateria(Materia materia) {
        if (materia != null && buscarMateria(materia.getCodigo()) == null) {
            materias.add(materia);
        }
    }

    public Materia buscarMateria(String codigo) {
        if (codigo == null) {
            return null;
        }

        for (Materia materia : materias) {
            if (materia.getCodigo().equalsIgnoreCase(codigo)) {
                return materia;
            }
        }
        return null;
    }

    public LinkedList<Materia> getMaterias() {
        return materias;
    }

    public String inscribirEstudiante(String id, String codigo)
            throws Excepciones.EstudianteNoEncontradoException, Excepciones.PreRequisitoNoAprobadoException {

        Estudiante estudiante = buscarEstudiante(id);
        Materia materia = buscarMateria(codigo);

        if (estudiante == null) {
            throw new Excepciones.EstudianteNoEncontradoException("No existe el estudiante con ID: " + id);
        }

        if (materia == null) {
            return "No existe la materia con código: " + codigo;
        }

        if (materia.estaInscrito(id) || materia.getColaEspera().contains(id)) {
            return "El estudiante ya está inscrito o en espera.";
        }

        for (Materia requisito : materia.getPrerrequisitos()) {
            if (!estudiante.getHistorialMaterias().contains(requisito)) {
                throw new Excepciones.PreRequisitoNoAprobadoException(
                    "No cumple el prerrequisito: " + requisito.getCodigo()
                );
            }
        }

        LinkedList<EstadoMateria> antes = new LinkedList<>();
        antes.add(new EstadoMateria(materia));

        String resultado;

        if (materia.tieneCupo()) {
            materia.getInscritos().add(id);
            resultado = "Inscripción realizada correctamente.";
        } else {
            try {
                throw new Excepciones.CupoLlenoException("La materia " + codigo + " alcanzó su cupo máximo.");
            } catch (Excepciones.CupoLlenoException e) {
                materia.getColaEspera().offer(id);
                resultado = e.getMessage() + " Estudiante agregado a la cola de espera.";
            }
        }

        LinkedList<EstadoMateria> despues = new LinkedList<>();
        despues.add(new EstadoMateria(materia));

        guardarAccion(
            "inscribir estudiante",
            () -> restaurarEstadosMaterias(antes),
            () -> restaurarEstadosMaterias(despues)
        );

        return resultado;
    }

    public boolean cancelarInscripcion(String id, String codigo) {
        Materia materia = buscarMateria(codigo);

        if (materia == null || !materia.estaInscrito(id)) {
            return false;
        }

        LinkedList<EstadoMateria> antes = new LinkedList<>();
        antes.add(new EstadoMateria(materia));

        materia.getInscritos().remove(id);
        asignarSiguienteEnEspera(materia);

        LinkedList<EstadoMateria> despues = new LinkedList<>();
        despues.add(new EstadoMateria(materia));

        guardarAccion(
            "cancelar inscripción",
            () -> restaurarEstadosMaterias(antes),
            () -> restaurarEstadosMaterias(despues)
        );

        return true;
    }

    public String retirarPrimeroDeEspera(String codigo) throws Excepciones.ColaDeEsperaVaciaException {
        Materia materia = buscarMateria(codigo);

        if (materia == null || materia.getColaEspera().isEmpty()) {
            throw new Excepciones.ColaDeEsperaVaciaException(
                "No hay estudiantes en espera para la materia " + codigo
            );
        }

        return materia.getColaEspera().poll();
    }

    private void asignarSiguienteEnEspera(Materia materia) {
        while (materia.tieneCupo() && !materia.getColaEspera().isEmpty()) {
            String siguiente = materia.getColaEspera().poll();

            if (estudiantes.containsKey(siguiente) && !materia.estaInscrito(siguiente)) {
                materia.getInscritos().add(siguiente);
                System.out.println(
                    "Cupo asignado al estudiante " + siguiente + " en " + materia.getCodigo()
                );
            }
        }
    }

    // -------------------- PROCESAMIENTO CSV --------------------

    public void agregarSolicitudBatch(String solicitud) {
        if (solicitud != null && !solicitud.trim().isEmpty()) {
            solicitudesBatch.offer(solicitud.trim());
        }
    }

    public int cargarSolicitudesBatch(String ruta) throws Excepciones.ArchivoInvalidoException {
        if (ruta == null || !ruta.toLowerCase().endsWith(".csv")) {
            throw new Excepciones.ArchivoInvalidoException("El archivo debe tener extensión .csv.");
        }

        int cantidad = 0;

        try (BufferedReader lector = new BufferedReader(new FileReader(ruta))) {
            String linea;

            while ((linea = lector.readLine()) != null) {
                linea = linea.trim();

                if (!linea.isEmpty()
                        && !linea.startsWith("#")
                        && !linea.toLowerCase().startsWith("idestudiante,")) {
                    solicitudesBatch.offer(linea);
                    cantidad++;
                }
            }
        } catch (IOException e) {
            throw new Excepciones.ArchivoInvalidoException("No se pudo leer el CSV: " + e.getMessage());
        }

        if (cantidad == 0) {
            throw new Excepciones.ArchivoInvalidoException("El CSV no contiene solicitudes válidas.");
        }

        return cantidad;
    }

    public void procesarSolicitudesBatch() {
        int exitosas = 0;
        int fallidas = 0;
        LinkedList<String> motivos = new LinkedList<>();

        while (!solicitudesBatch.isEmpty()) {
            String solicitud = solicitudesBatch.poll();
            String[] datos = solicitud.split(",");

            if (datos.length != 2) {
                fallidas++;
                motivos.add(solicitud + " -> formato inválido");
                continue;
            }

            try {
                String resultado = inscribirEstudiante(datos[0].trim(), datos[1].trim());

                if (resultado.startsWith("Inscripción realizada")
                        || resultado.startsWith("La materia")) {
                    exitosas++;
                } else {
                    fallidas++;
                    motivos.add(solicitud + " -> " + resultado);
                }
            } catch (Excepciones.EstudianteNoEncontradoException | Excepciones.PreRequisitoNoAprobadoException e) {
                fallidas++;
                motivos.add(solicitud + " -> " + e.getMessage());
            }
        }

        System.out.println("\n--- RESUMEN DEL PROCESAMIENTO ---");
        System.out.println("Solicitudes exitosas: " + exitosas);
        System.out.println("Solicitudes fallidas: " + fallidas);

        for (String motivo : motivos) {
            System.out.println(motivo);
        }
    }

    public Queue<String> getSolicitudesBatch() {
        return solicitudesBatch;
    }

    // -------------------- AULAS Y HORARIOS --------------------

    public void agregarAula(Aula aula) {
        if (aula != null) {
            aulas.put(aula.getNombre(), aula);
        }
    }

    public TreeMap<String, Aula> getAulas() {
        return aulas;
    }

    public boolean cambiarHorario(Aula aula, int dia, int hora, int duracion, boolean reservar)
            throws Excepciones.HorarioConflictivoException {

        if (aula == null) {
            throw new Excepciones.HorarioConflictivoException("El aula no existe.");
        }

        boolean realizado = reservar
            ? aula.reservar(dia, hora, duracion)
            : aula.liberar(dia, hora, duracion);

        if (!realizado) {
            throw new Excepciones.HorarioConflictivoException(
                "Horario conflictivo o datos de día, hora o duración inválidos."
            );
        }

        guardarAccion(
            "cambiar horario",
            () -> {
                if (reservar) aula.liberar(dia, hora, duracion);
                else aula.reservar(dia, hora, duracion);
            },
            () -> {
                if (reservar) aula.reservar(dia, hora, duracion);
                else aula.liberar(dia, hora, duracion);
            }
        );

        return true;
    }

    // -------------------- PROFESORES Y FACULTADES --------------------

    public void agregarProfesor(Profesor profesor) {
        if (profesor != null) {
            profesores.add(profesor);
        }
    }

    public LinkedList<Profesor> getProfesores() {
        return profesores;
    }

    public Facultad[] getFacultades() {
        return facultades;
    }

    public void agregarFacultad(int posicion, Facultad facultad) {
        if (posicion >= 0 && posicion < facultades.length) {
            facultades[posicion] = facultad;
        }
    }

    // -------------------- NOTAS Y REPORTES --------------------

    public void registrarNota(String id, int semestre, int materia, double nota)
            throws Excepciones.EstudianteNoEncontradoException {

        Estudiante estudiante = buscarEstudiante(id);

        if (estudiante == null) {
            throw new Excepciones.EstudianteNoEncontradoException("No existe el estudiante con ID: " + id);
        }

        if (semestre < 0 || semestre >= 10
                || materia < 0 || materia >= 20
                || nota < 0 || nota > 5) {
            System.out.println("Datos de nota inválidos. Use semestre 1-10, materia 1-20 y nota 0-5.");
            return;
        }

        Double anterior = estudiante.getNotas()[semestre][materia];
        estudiante.getNotas()[semestre][materia] = nota;

        guardarAccion(
            "registrar nota",
            () -> estudiante.getNotas()[semestre][materia] = anterior,
            () -> estudiante.getNotas()[semestre][materia] = nota
        );
    }

    public void mostrarReporteNotas(Estudiante estudiante) {
        if (estudiante == null) {
            System.out.println("Estudiante no encontrado.");
            return;
        }

        Double[][] notas = estudiante.getNotas();
        double sumaTotal = 0;
        int cantidadTotal = 0;

        System.out.println("--- Reporte académico: " + estudiante.getNombre() + " ---");

        for (int semestre = 0; semestre < 10; semestre++) {
            double sumaSemestre = 0;
            int cantidadSemestre = 0;

            for (int materia = 0; materia < 20; materia++) {
                Double nota = notas[semestre][materia];

                if (nota != null) {
                    sumaSemestre += nota;
                    sumaTotal += nota;
                    cantidadSemestre++;
                    cantidadTotal++;

                    if (nota < 3.0) {
                        System.out.println(
                            "Reprobada: semestre " + (semestre + 1)
                            + ", materia " + (materia + 1) + ", nota " + nota
                        );
                    }
                }
            }

            if (cantidadSemestre > 0) {
                System.out.println(
                    "Promedio semestre " + (semestre + 1) + ": "
                    + (sumaSemestre / cantidadSemestre)
                );
            }
        }

        if (cantidadTotal == 0) {
            System.out.println("Todavía no hay notas registradas.");
        } else {
            System.out.println(
                "Promedio acumulado: " + String.format("%.2f", sumaTotal / cantidadTotal)
            );
        }
    }

    public Stack<String> getReportes() {
        return reportes;
    }

    // -------------------- CAMPUS Y DIJKSTRA --------------------

    public void configurarCampus(String[] nombres, int[][] matriz) {
        if (nombres == null || matriz == null || nombres.length != matriz.length) {
            System.out.println("Los datos del campus no son válidos.");
            return;
        }

        for (int i = 0; i < matriz.length; i++) {
            if (matriz[i] == null || matriz[i].length != nombres.length) {
                System.out.println("La matriz debe ser cuadrada.");
                return;
            }
        }

        edificios = nombres;
        distancias = matriz;
    }

    public String[] getEdificios() {
        return edificios;
    }

    public int[][] getDistancias() {
        return distancias;
    }

    public void rutaMasCorta(int origen, int destino) {
        if (edificios == null || distancias == null || edificios.length < 5
                || origen < 0 || destino < 0
                || origen >= edificios.length || destino >= edificios.length) {
            System.out.println("Configure al menos 5 edificios e índices válidos.");
            return;
        }

        int cantidad = edificios.length;
        int[] distancia = new int[cantidad];
        int[] anterior = new int[cantidad];
        boolean[] visitado = new boolean[cantidad];

        Arrays.fill(distancia, Integer.MAX_VALUE);
        Arrays.fill(anterior, -1);
        distancia[origen] = 0;

        for (int paso = 0; paso < cantidad; paso++) {
            int actual = -1;

            for (int i = 0; i < cantidad; i++) {
                if (!visitado[i]
                        && (actual == -1 || distancia[i] < distancia[actual])) {
                    actual = i;
                }
            }

            if (actual == -1 || distancia[actual] == Integer.MAX_VALUE) {
                break;
            }

            visitado[actual] = true;

            for (int vecino = 0; vecino < cantidad; vecino++) {
                int peso = distancias[actual][vecino];

                if (peso > 0 && !visitado[vecino]
                        && distancia[actual] + peso < distancia[vecino]) {
                    distancia[vecino] = distancia[actual] + peso;
                    anterior[vecino] = actual;
                }
            }
        }

        if (distancia[destino] == Integer.MAX_VALUE) {
            System.out.println("No existe una ruta entre esos edificios.");
            return;
        }

        LinkedList<Integer> camino = new LinkedList<>();

        for (int edificio = destino; edificio != -1; edificio = anterior[edificio]) {
            camino.addFirst(edificio);
        }

        System.out.print("Ruta más corta: ");

        for (int i = 0; i < camino.size(); i++) {
            if (i > 0) System.out.print(" -> ");
            System.out.print(edificios[camino.get(i)]);
        }

        System.out.println(" | Distancia total: " + distancia[destino] + " metros");
    }
}
