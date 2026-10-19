package AF2;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private String cliente;
    private List<Plato> platos = new ArrayList<>();

    public Pedido(String cliente) {
        this.cliente = cliente;
    }

    // Con contains Mira si el plato ya estaba y avisa, y con add aqui se agrego un plato mas a la lista del mismo
    
    public String getCliente() { return cliente; }

    public void agregarPlato(Plato plato) {
        if (platos.contains(plato)) {                       
            System.out.println("Ese plato ya estaba, se agrega otra unidad.");
        }
        platos.add(plato);                                 
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