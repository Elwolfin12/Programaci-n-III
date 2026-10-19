package AF2;

import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    // Se crea el puesto con 3 platos iniciales.
    public static void main(String[] args) {
        PuestoComidas puesto = new PuestoComidas();
        puesto.agregarPlato(new Plato("Hamburguesa", 12000));
        puesto.agregarPlato(new Plato("Perro caliente", 8000));
        puesto.agregarPlato(new Plato("Gaseosa", 3000));

        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n1. Ver menu");
            System.out.println("2. Agregar plato");
            System.out.println("3. Quitar plato");
            System.out.println("4. Crear pedido");
            System.out.println("5. Agregar plato a pedido");
            System.out.println("6. Ver pedidos");
            System.out.println("7. Entregar pedido");
            System.out.println("8. Cancelar todos los pedidos");
            System.out.println("0. Salir");
            opcion = leerNumero("Opcion: ");

            switch (opcion) {
                case 1:
                    puesto.mostrarMenu();
                    break;
                case 2:
                    puesto.agregarPlato(new Plato(leerTexto("Nombre: "), leerNumero("Precio: ")));
                    break;
                case 3:
                    puesto.quitarPlato(leerTexto("Plato a quitar: "));
                    break;
                case 4:
                    String cliente = leerTexto("Cliente: ");
                    boolean urgente = leerTexto("Urgente? (s/n): ").equalsIgnoreCase("s");
                    puesto.registrarPedido(new Pedido(cliente), urgente);
                    break;
                case 5:
                    Pedido pedido = puesto.buscarPedido(leerTexto("Cliente: "));
                    puesto.mostrarMenu();
                    Plato plato = puesto.obtenerPlato(leerNumero("Numero del plato: "));
                    if (pedido == null || plato == null) {
                        System.out.println("Pedido o plato invalido.");
                    } else {
                        pedido.agregarPlato(plato);
                    }
                    break;
                case 6:
                    puesto.mostrarPedidos();
                    break;
                case 7:
                    puesto.entregarPedido(leerTexto("Cliente: "));
                    break;
                case 8:
                    puesto.cancelarTodos();
                    break;
            }
        }
    }

    // Lee una linea completa del teclado.

    static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine().trim();
    }

    // Lee un entero; si escriben letras devuelve -1 osea 0.
    
    static int leerNumero(String mensaje) {
        try {
            return Integer.parseInt(leerTexto(mensaje));
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}