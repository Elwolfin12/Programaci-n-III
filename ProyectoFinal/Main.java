import estructuras.Universidad;
import excepciones.Excepciones;
import java.util.Scanner;
import modelo.*;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final Universidad uni = new Universidad();

    public static void main(String[] args) throws Exception {
        int op;
        do {
            mostrarMenu();
            op = entero("Seleccione una opcion: ");

            switch (op) {
                case 1 -> registrarEstudiante();
                case 2 -> buscarEstudiante();
                case 3 -> listarEstudiantes();
                case 4 -> eliminarEstudiante();
                case 5 -> crearMateria();
                case 6 -> agregarPrerequisito();
                case 7 -> mostrarPrerequisitos();
                case 8 -> inscribirEstudiante();
                case 9 -> cancelarInscripcion();
                case 10 -> mostrarColaEspera();
                case 11 -> cambiarHorario(true);
                case 12 -> cambiarHorario(false);
                case 13 -> consultarDisponibilidad();
                case 14 -> agregarConexion();
                case 15 -> calcularRuta();
                case 16 -> registrarNota();
                case 17 -> verReporte();
                case 18 -> navegarReportes();
                case 19 -> deshacer();
                case 20 -> rehacer();
                case 21 -> procesarCSV();
                case 22 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opcion no valida. Intente nuevamente.");
            }
        } while (op != 22);
    }

    private static void mostrarMenu() {
        System.out.println("\n============================================================");
        System.out.println("       PLANIFICACION ACADEMICA - SISTEMA UNIVERSITARIO");
        System.out.println("============================================================");
        System.out.println("=== GESTION DE ESTUDIANTES ===");
        System.out.println("1. Registrar estudiante");
        System.out.println("2. Buscar estudiante por ID");
        System.out.println("3. Listar todos los estudiantes");
        System.out.println("4. Eliminar estudiante");
        System.out.println("=== GESTION DE MATERIAS ===");
        System.out.println("5. Crear materia");
        System.out.println("6. Agregar pre-requisito");
        System.out.println("7. Mostrar pre-requisitos");
        System.out.println("8. Inscribir estudiante");
        System.out.println("9. Cancelar inscripcion");
        System.out.println("10. Mostrar cola de espera");
        System.out.println("=== GESTION DE HORARIOS ===");
        System.out.println("11. Reservar horario en aula");
        System.out.println("12. Liberar horario");
        System.out.println("13. Consultar disponibilidad");
        System.out.println("=== RUTAS ENTRE EDIFICIOS ===");
        System.out.println("14. Agregar conexion entre edificios");
        System.out.println("15. Calcular ruta mas corta");
        System.out.println("=== REPORTES ACADEMICOS ===");
        System.out.println("16. Registrar nota");
        System.out.println("17. Ver reporte academico");
        System.out.println("18. Navegador de reportes (atras/adelante)");
        System.out.println("=== SISTEMA DESHACER/REHACER ===");
        System.out.println("19. Deshacer ultima operacion");
        System.out.println("20. Rehacer ultima operacion");
        System.out.println("=== PROCESAMIENTO POR LOTES ===");
        System.out.println("21. Procesar archivo CSV");
        System.out.println("=== SALIR ===");
        System.out.println("22. Salir");
        System.out.println("============================================================");
    }

    private static void registrarEstudiante() {
        Estudiante e = new Estudiante(texto("Nombre: "), texto("ID: "),
                texto("Email: "), entero("Semestre actual (1-10): "));
        System.out.println(uni.registrarEstudiante(e)
                ? "Estudiante registrado correctamente." : "Ese ID ya existe.");
    }

    private static void buscarEstudiante() {
        Estudiante e = uni.buscarEstudiante(texto("ID del estudiante: "));
        if (e == null) System.out.println("Estudiante no encontrado.");
        else e.mostrarInformacion();
    }

    private static void listarEstudiantes() {
        if (uni.getEstudiantes().isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
            return;
        }
        for (Estudiante e : uni.getEstudiantes().values()) e.mostrarInformacion();
    }

    private static void eliminarEstudiante() {
        String id = texto("ID del estudiante que desea eliminar: ");
        System.out.println(uni.eliminarEstudiante(id)
                ? "Estudiante eliminado. Puede deshacer esta operacion."
                : "No se encontro el estudiante.");
    }

    private static void crearMateria() {
        String codigo = texto("Codigo de la materia: ");
        if (uni.buscarMateria(codigo) != null) {
            System.out.println("Ya existe una materia con ese codigo.");
            return;
        }
        String nombre = texto("Nombre: ");
        int cupos = entero("Cupos maximos: ");
        int creditos = entero("Creditos: ");
        uni.agregarMateria(new Materia(codigo, nombre, cupos, creditos));
        System.out.println("Materia creada correctamente.");
    }

    private static void agregarPrerequisito() {
        Materia materia = uni.buscarMateria(texto("Codigo de la materia: "));
        Materia prerequisito = uni.buscarMateria(texto("Codigo del pre-requisito: "));
        if (materia == null || prerequisito == null) {
            System.out.println("No se encontro alguna de las materias.");
        } else if (materia == prerequisito) {
            System.out.println("Una materia no puede ser su propio pre-requisito.");
        } else {
            materia.agregarPrerrequisito(prerequisito);
            System.out.println("Pre-requisito agregado.");
        }
    }

    private static void mostrarPrerequisitos() {
        Materia materia = uni.buscarMateria(texto("Codigo de la materia: "));
        if (materia == null) {
            System.out.println("Materia no encontrada.");
            return;
        }
        System.out.println("Pre-requisitos de " + materia.getNombre() + ":");
        if (materia.getPrerrequisitos().isEmpty()) {
            System.out.println("Esta materia no tiene pre-requisitos.");
        } else {
            for (Materia p : materia.getPrerrequisitos())
                System.out.println(p.getCodigo() + " - " + p.getNombre());
        }
    }

    private static void inscribirEstudiante() {
        try {
            String id = texto("ID del estudiante: ");
            String codigo = texto("Codigo de la materia: ");
            System.out.println(uni.inscribirEstudiante(id, codigo));
        } catch (Excepciones.EstudianteNoEncontradoException |
                 Excepciones.PreRequisitoNoAprobadoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void cancelarInscripcion() {
        String id = texto("ID del estudiante: ");
        String codigo = texto("Codigo de la materia: ");
        System.out.println(uni.cancelarInscripcion(id, codigo)
                ? "Inscripcion cancelada." : "No se encontro esa inscripcion.");
    }

    private static void mostrarColaEspera() {
        Materia m = uni.buscarMateria(texto("Codigo de la materia: "));
        if (m == null) {
            System.out.println("Materia no encontrada.");
            return;
        }
        System.out.println("Cola de espera: " + m.getColaEspera());
    }

    private static Aula seleccionarAula() {
        if (uni.getAulas().isEmpty()) {
            System.out.println("No hay aulas. Registre un aula primero.");
            return null;
        }
        System.out.println("Aulas disponibles:");
        for (Aula aula : uni.getAulas().values()) System.out.println("- " + aula);
        Aula aula = uni.getAulas().get(texto("Nombre del aula: "));
        if (aula == null) System.out.println("Aula no encontrada.");
        return aula;
    }

    private static void cambiarHorario(boolean reservar) throws Exception {
        Aula aula = seleccionarAula();
        if (aula == null) return;
        int dia = entero("Dia (0 domingo, 1 lunes, ..., 6 sabado): ");
        int hora = entero("Hora inicial (0-23): ");
        int duracion = entero("Duracion en horas: ");
        try {
            uni.cambiarHorario(aula, dia, hora, duracion, reservar);
            System.out.println(reservar ? "Horario reservado." : "Horario liberado.");
        } catch (Excepciones.HorarioConflictivoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void consultarDisponibilidad() {
        Aula aula = seleccionarAula();
        if (aula == null) return;
        int dia = entero("Dia (0 domingo, 1 lunes, ..., 6 sabado): ");
        int hora = entero("Hora (0-23): ");
        System.out.println(aula.consultarDisponibilidad(dia, hora)
                ? "El horario esta disponible." : "El horario esta ocupado o los datos no son validos.");
    }

    private static void agregarConexion() {
        String[] edificios = uni.getEdificios();
        int[][] distancias = uni.getDistancias();

        if (edificios == null || edificios.length == 0 || distancias == null) {
            int n = entero("Numero de edificios que tendra el campus (minimo 5): ");
            if (n < 5 || n > 20) {
                System.out.println("Debe ingresar entre 5 y 20 edificios.");
                return;
            }
            edificios = new String[n];
            distancias = new int[n][n];
            for (int i = 0; i < n; i++) edificios[i] = texto("Nombre del edificio " + (i + 1) + ": ");
            uni.configurarCampus(edificios, distancias);
        }

        mostrarEdificios(edificios);
        int a = entero("Indice del primer edificio: ");
        int b = entero("Indice del segundo edificio: ");
        if (a < 0 || b < 0 || a >= edificios.length || b >= edificios.length || a == b) {
            System.out.println("Indices no validos.");
            return;
        }
        int distancia = entero("Distancia en metros: ");
        if (distancia <= 0) {
            System.out.println("La distancia debe ser mayor que cero.");
            return;
        }
        distancias[a][b] = distancia;
        distancias[b][a] = distancia;
        uni.configurarCampus(edificios, distancias);
        System.out.println("Conexion agregada correctamente.");
    }

    private static void calcularRuta() {
        String[] edificios = uni.getEdificios();
        if (edificios == null || edificios.length == 0) {
            System.out.println("Primero debe agregar los edificios y sus conexiones.");
            return;
        }
        mostrarEdificios(edificios);
        int origen = entero("Indice del edificio de origen: ");
        int destino = entero("Indice del edificio de destino: ");
        uni.rutaMasCorta(origen, destino);
    }

    private static void mostrarEdificios(String[] edificios) {
        System.out.println("Edificios (use el indice mostrado):");
        for (int i = 0; i < edificios.length; i++)
            System.out.println(i + ". " + edificios[i]);
    }

    private static void registrarNota() throws Exception {
        String id = texto("ID del estudiante: ");
        if (uni.buscarEstudiante(id) == null) {
            System.out.println("Estudiante no encontrado.");
            return;
        }
        int semestre = entero("Semestre (1-10): ") - 1;
        int materia = entero("Numero de materia (1-20): ") - 1;
        double nota = decimal("Nota (0-5): ");
        try {
            uni.registrarNota(id, semestre, materia, nota);
            System.out.println("Nota registrada.");
        } catch (Excepciones.EstudianteNoEncontradoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void verReporte() {
        Estudiante e = uni.buscarEstudiante(texto("ID del estudiante: "));
        if (e == null) {
            System.out.println("Estudiante no encontrado.");
            return;
        }
        uni.getReportes().push("Reporte academico de " + e.getNombre());
        uni.mostrarReporteNotas(e);
    }

    private static void navegarReportes() {
        if (uni.getReportes().empty()) {
            System.out.println("No hay reportes en el historial.");
            return;
        }
        System.out.println("Historial de reportes (del mas reciente al mas antiguo):");
        for (int i = uni.getReportes().size() - 1; i >= 0; i--)
            System.out.println("- " + uni.getReportes().get(i));
        System.out.println("1. Atras (retirar reporte actual)");
        System.out.println("0. Volver");
        if (entero("Seleccione: ") == 1) {
            System.out.println("Reporte retirado: " + uni.getReportes().pop());
        }
    }

    private static void deshacer() throws Exception {
        try {
            uni.deshacer();
            System.out.println("Ultima operacion deshecha.");
        } catch (Excepciones.PilaDeshacerVaciaException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void rehacer() {
        uni.rehacer();
        System.out.println("Operacion de la pila de rehacer procesada.");
    }

    private static void procesarCSV() throws Exception {
        String ruta = texto("Ruta del archivo CSV: ");
        try {
            int cantidad = uni.cargarSolicitudesBatch(ruta);
            System.out.println("Solicitudes cargadas: " + cantidad);
            uni.procesarSolicitudesBatch();
        } catch (Excepciones.ArchivoInvalidoException e) {
            System.out.println("Error de archivo: " + e.getMessage());
        }
    }

    private static String texto(String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine().trim();
    }

    private static int entero(String mensaje) {
        while (true) {
            try {
                return Integer.parseInt(texto(mensaje));
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un numero entero valido.");
            }
        }
    }

    private static double decimal(String mensaje) {
        while (true) {
            try {
                return Double.parseDouble(texto(mensaje));
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un numero valido.");
            }
        }
    }
}
