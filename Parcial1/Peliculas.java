public class Peliculas {

    private String nombre;
    private String idioma;
    private String tipo;   // "35mm" o "3D"
    private int duracion;  // duracion en minutos

    // contructores

    // Constructor vacio
    public Peliculas() {
    }

    // Constructor completo: recibe los 4 datos y los guarda en los atributos
    // (this.nombre = atributo de la clase, nombre = parametro recibido)
    public Peliculas(String nombre, String idioma, String tipo, int duracion) {
        this.nombre = nombre;
        this.idioma = idioma;
        this.tipo = tipo;
        this.duracion = duracion;
    }

    // gets y sets 
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    // Devuelve true si la pelicula es 3D. Se usa para validar las reglas de
    // sala (la sala 3 solo proyecta 3D; las salas 1 y 2 no proyectan 3D).
    public boolean es3D() {
        return tipo.equalsIgnoreCase("3D");
    }

    // Convierte la pelicula en una linea de texto para poder imprimirla.
    // Ejemplo: "Avatar | Espanol | 3D | 120 min"
    @Override
    public String toString() {
        return nombre + " | " + idioma + " | " + tipo + " | " + duracion + " min";
    }
}
