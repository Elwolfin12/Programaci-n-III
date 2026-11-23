package ProyectoFinal.modelo;

import java.util.LinkedList;
import java.util.Queue;

    // Guarda los datos de una materia y sus estudiantes.
    // LinkedList almacena prerrequisitos e inscritos.
    // Queue atiende la lista de espera en orden de llegada.

public class Materia {
    private String codigo, nombre;
    private int cuposMaximos, creditos;
    private LinkedList<Materia> prerrequisitos = new LinkedList<>();
    private LinkedList<String> inscritos = new LinkedList<>();
    private Queue<String> colaEspera = new LinkedList<>();

    public Materia(String codigo, String nombre, int cuposMaximos, int creditos) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.cuposMaximos = cuposMaximos;
        this.creditos = creditos;
    }
    // Constructor de compatibilidad.
    public Materia(String codigo, String nombre, int creditos) { this(codigo, nombre, 30, creditos); }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public int getCuposMaximos() { return cuposMaximos; }
    public int getCreditos() { return creditos; }
    public LinkedList<Materia> getPrerrequisitos() { return prerrequisitos; }
    public LinkedList<String> getInscritos() { return inscritos; }
    public Queue<String> getColaEspera() { return colaEspera; }

    public void agregarPrerrequisito(Materia materia) {
        if (materia != null && materia != this && !prerrequisitos.contains(materia)) prerrequisitos.add(materia);
    }
    public boolean tieneCupo() { return inscritos.size() < cuposMaximos; }
    public boolean estaInscrito(String id) { return inscritos.contains(id); }

    @Override
    public String toString() {
        return codigo + " - " + nombre + " | Créditos: " + creditos + " | Cupos: "
                + inscritos.size() + "/" + cuposMaximos + " | En espera: " + colaEspera.size();
    }
}
