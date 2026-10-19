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

  // Pedidos

    // add al final, o add(0, objeto) si es urgente el pedido osea le da prioridad

    public void registrarPedido(Pedido pedido, boolean urgente) {
        if (urgente) {
            pedidos.add(0, pedido);
        } else {
            pedidos.add(pedido);
        }
    }

    //Devuelve el pedido del cliente o null si no existe.

    public Pedido buscarPedido(String cliente) {
        for (Pedido p : pedidos) {
            if (p.getCliente().equalsIgnoreCase(cliente)) {
                return p;
            }
        }
        return null;
    }

    // remove saca el pedido y muestra el total cobrado.

    public void entregarPedido(String cliente) {
        Pedido pedido = buscarPedido(cliente);
        if (pedido == null) {
            System.out.println("No existe ese pedido.");
        } else {
            pedidos.remove(pedido);
            System.out.println("Pedido entregado. Total cobrado: $" + pedido.calcularTotal());
        }
    }

    // clear borra todos los pedidos

    public void cancelarTodos() {
        pedidos.clear();
        System.out.println("Pedidos cancelados.");
    }

    public void mostrarPedidos() {
        if (pedidos.isEmpty()) {                                  
            System.out.println("No hay pedidos pendientes.");
            return;
        }
        System.out.println("=== " + pedidos.size() + " PEDIDOS ===");   
        for (Pedido p : pedidos) {
            p.mostrar();
        }
    }
}