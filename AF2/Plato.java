package AF2;

public class Plato {
    private String nombre;
    private int precio;

    // aqui se guarda el nombre y precio

    public Plato(String nombre, int precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() { return nombre; }
    public int getPrecio() { return precio; }

    // Muetra como se imprime ya sea hamburguesa, perro o algun otro 

    public String toString() {
        return nombre + " ($" + precio + ")";
    }
}