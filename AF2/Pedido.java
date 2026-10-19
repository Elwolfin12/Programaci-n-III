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



    
}