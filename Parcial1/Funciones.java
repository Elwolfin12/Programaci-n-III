 // Una funcion es: una pelicula, en una sala, a una hora.
// Cada funcion tiene su propia sala (sus propios asientos).
public class Funciones {
 
    private Peliculas pelicula;
    private String hora;
    private int numeroSala;
    private sala objetoSala;
 
    // Constructor: al crear la funcion tambien se crea su sala
    public Funciones(Peliculas pelicula, int numeroSala, String hora) {
        this.pelicula = pelicula;
        this.numeroSala = numeroSala;
        this.hora = hora;
        this.objetoSala = new sala(numeroSala);
    }
 
    public Peliculas getPelicula() {
        return pelicula;
    }
 
    public String getHora() {
        return hora;
    }
 
    public int getNumeroSala() {
        return numeroSala;
    }
 
    public sala getObjetoSala() {
        return objetoSala;
    }
 
    // Cuantas sillas libres tiene esta funcion
    public int getDisponibles() {
        return objetoSala.contarDisponibles();
    }
 
    // Imprime los datos de la funcion
    public void mostrarInfo() {
        System.out.println("Sala " + numeroSala + " | " + hora + " | " + pelicula.getNombre() + " (" + pelicula.getTipo() + ")");
    }
 
    // Convierte el numero de franja (1, 2 o 3) en el horario.
    // Es static para poder usarlo asi: Funciones.obtenerHorario(1)
    public static String obtenerHorario(int franja) {
        if (franja == 1) {
            return "14:00 - 16:30";
        } else if (franja == 2) {
            return "16:30 - 19:00";
        } else {
            return "19:00 - 21:00";
        }
    }
}
 