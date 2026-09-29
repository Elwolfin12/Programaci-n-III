// Clase que guarda los datos de una pelicula
public class Peliculas {

    // Atributos (private: solo se pueden usar desde esta clase)
    private String nombre;
    private String idioma;
    private String tipo;   // "35mm" o "3D"
    private int duracion;  // en minutos

    // Constructor: se ejecuta al crear la pelicula con "new Peliculas(...)"
    public Peliculas(String nombre, String idioma, String tipo, int duracion) {
        this.nombre = nombre;
        this.idioma = idioma;
        this.tipo = tipo;
        this.duracion = duracion;
    }

    // Getters: sirven para LEER los atributos desde otras clases
    public String getNombre() {
        return nombre;
    }

    public String getIdioma() {
        return idioma;
    }

    public String getTipo() {
        return tipo;
    }

    public int getDuracion() {
        return duracion;
    }

    // Setters: sirven para CAMBIAR los atributos desde otras clases
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    // Devuelve true si la pelicula es 3D, false si no
    public boolean esTresD() {
        return tipo.equals("3D");
    }

    // Imprime los datos de la pelicula
    public void mostrarInfo() {
        System.out.println(nombre + " | " + idioma + " | " + tipo + " | " + duracion + " min");
    }
}