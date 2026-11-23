package ProyectoFinal.modelo;

import java.util.LinkedList;

    // Hereda los datos comunes de Persona.
    // Las notas guardan hasta 10 semestres y 20 materias por semestre.
    // El historial conserva las materias que el estudiante ha cursado.

public class Estudiante extends Persona {
    private int semestre;
    private Double[][] notas = new Double[10][20];
    private LinkedList<Materia> historialMaterias = new LinkedList<>();

    public Estudiante(String nombre, String id, String email, int semestre) {
        super(nombre, id, email); this.semestre = semestre;
    }
    public int getSemestre() { return semestre; }
    public Double[][] getNotas() { return notas; }
    public LinkedList<Materia> getHistorialMaterias() { return historialMaterias; }
    public void agregarMateriaCursada(Materia materia) {
        if (materia != null && !historialMaterias.contains(materia)) historialMaterias.add(materia);
    }
    @Override
    public void mostrarInformacion() {
        System.out.println("Estudiante: " + getNombre() + " | ID: " + getId()
                + " | Email: " + getEmail() + " | Semestre: " + semestre);
    }
}
