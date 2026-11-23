package ProyectoFinal.modelo;

    // Clase base: guarda los datos que comparten estudiantes y profesores.
    // Es abstracta porque no se crean personas directamente.

public abstract class Persona {
    private String nombre, id, email;

    public Persona(String nombre, String id, String email) {
        this.nombre = nombre;
        this.id = id;
        this.email = email;
    }

    public String getNombre() { return nombre; }
    public String getId() { return id; }
    public String getEmail() { return email; }

    public abstract void mostrarInformacion();
}
