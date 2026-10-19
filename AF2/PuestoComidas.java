package AF2;

import java.util.ArrayList;
import java.util.List;

// Administra el menu y los pedidos pendientes.

public class PuestoComidas {
    private List<Plato> menu = new ArrayList<>();
    private List<Pedido> pedidos = new ArrayList<>();

    // Menu

    // add agrega al final del menu.
    public void agregarPlato(Plato plato) {
        menu.add(plato);
    }

    public Plato buscarPlato(String nombre) {
        for (Plato p : menu) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                return p;
            }
        }
        return null;
    }

    // remove quita el plato del menu

    public void quitarPlato(String nombre) {
        Plato plato = buscarPlato(nombre);
        if (plato == null) {
            System.out.println("No existe ese plato.");
        } else {
            menu.remove(plato);
            System.out.println("Plato quitado.");
        }
    }

    // size valida el rango; get saca el plato

    public Plato obtenerPlato(int numero) {
        if (numero >= 1 && numero <= menu.size()) {
            return menu.get(numero - 1);
        }
        return null;
    }

    public void mostrarMenu() {
        System.out.println("=== MENU ===");
        for (int i = 0; i < menu.size(); i++) {
            System.out.println((i + 1) + ". " + menu.get(i));
        }
    }


}
