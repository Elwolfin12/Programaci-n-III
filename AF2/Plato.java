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

    // recorre la lista con un for-each y suma los precios, y mostrar imprime 

    public int calcularTotal() {
        int total = 0;
        for (Plato p : platos) {
            total += p.getPrecio();
        }
        return total;
    }

    public void mostrar() {
        System.out.println("Cliente: " + cliente);
        for (int i = 0; i < platos.size(); i++) {           
            System.out.println("   " + (i + 1) + ". " + platos.get(i));
        }
        System.out.println("   Total: $" + calcularTotal());
    }
    
}