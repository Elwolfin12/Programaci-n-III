package ProyectoFinal.modelo;

    // Cada fila representa un dia y cada columna una hora.
    // false = libre; true = ocupada.

public class Aula {
    private String nombre;
    private int capacidad;
    // true significa que la hora está ocupada.
    private boolean[][] disponibilidad = new boolean[7][24];

    public Aula(String nombre, int capacidad) {
        this.nombre = nombre;
        this.capacidad = capacidad;
    }

    public String getNombre() { return nombre; }
    public int getCapacidad() { return capacidad; }

    public boolean reservar(int dia, int hora, int duracion) {
        if (!rangoValido(dia, hora, duracion)) return false;
        for (int h = hora; h < hora + duracion; h++)
            if (disponibilidad[dia][h]) return false;
        for (int h = hora; h < hora + duracion; h++) disponibilidad[dia][h] = true;
        return true;
    }

    public boolean liberar(int dia, int hora, int duracion) {
        if (!rangoValido(dia, hora, duracion)) return false;
        for (int h = hora; h < hora + duracion; h++)
            if (!disponibilidad[dia][h]) return false;
        for (int h = hora; h < hora + duracion; h++) disponibilidad[dia][h] = false;
        return true;
    }

    public boolean consultarDisponibilidad(int dia, int hora) {
        return dia >= 0 && dia < 7 && hora >= 0 && hora < 24 && !disponibilidad[dia][hora];
    }

    private boolean rangoValido(int dia, int hora, int duracion) {
        return dia >= 0 && dia < 7 && hora >= 0 && duracion > 0 && hora + duracion <= 24;
    }

    // Métodos de compatibilidad con la versión anterior.
    public boolean asignarHorario(int dia, int hora) { return reservar(dia, hora, 1); }
    public void liberarHorario(int dia, int hora) { liberar(dia, hora, 1); }
    public boolean estaOcupada(int dia, int hora) { return !consultarDisponibilidad(dia, hora); }

    @Override
    public String toString() { return nombre + " | Capacidad: " + capacidad; }
}
