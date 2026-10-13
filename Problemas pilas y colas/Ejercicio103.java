import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Ejercicio103 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Cola con los carritos disponibles (numerados del 1 al 25)
        Queue<Integer> carritos = new LinkedList<>();
        for (int i = 1; i <= 25; i++) {
            carritos.add(i);
        }

        // Cola de clientes que llegaron y esperan a que haya un carrito
        Queue<Cliente> esperandoCarrito = new LinkedList<>();

        // Clientes que estan comprando (ya tienen carrito)
        ArrayList<Cliente> comprando = new ArrayList<>();

        // Las 3 colas de las cajas de pago
        ArrayList<Queue<Cliente>> cajas = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            cajas.add(new LinkedList<Cliente>());
        }

        int siguienteCliente = 1;          // numero que recibe el proximo cliente
        int opcion = -1;

        while (opcion != 0) {
            System.out.println();
            System.out.println("1. Llega un cliente");
            System.out.println("2. Un cliente termina de comprar");
            System.out.println("3. Una caja atiende (cobra) a un cliente");
            System.out.println("4. Ver estado del supermercado");
            System.out.println("0. Salir");
            System.out.print("Opcion: ");
            opcion = leerNumero(sc);

            if (opcion == 1) {
                // Llega un cliente nuevo
                Cliente nuevo = new Cliente(siguienteCliente);
                siguienteCliente++;

                if (carritos.isEmpty()) {
                    // No hay carritos: espera a que alguno quede libre
                    esperandoCarrito.add(nuevo);
                    System.out.println("No hay carritos. Cliente " + nuevo.numero + " espera.");
                } else {
                    nuevo.carrito = carritos.poll();
                    comprando.add(nuevo);
                    System.out.println("Cliente " + nuevo.numero + " toma el carrito " + nuevo.carrito);
                }

            } else if (opcion == 2) {
                // Un cliente termina su compra y va a la caja con menos gente
                System.out.print("Numero del cliente que termino: ");
                int numero = leerNumero(sc);

                Cliente encontrado = null;
                for (int i = 0; i < comprando.size(); i++) {
                    if (comprando.get(i).numero == numero) {
                        encontrado = comprando.get(i);
                    }
                }

                if (encontrado == null) {
                    System.out.println("Ese cliente no esta comprando.");
                } else {
                    comprando.remove(encontrado);
                    int caja = cajaConMenosGente(cajas);
                    cajas.get(caja).add(encontrado);      // se pone al final de la cola
                    System.out.println("Cliente " + encontrado.numero + " se coloca en la caja " + (caja + 1));
                }

            } else if (opcion == 3) {
                // Una caja cobra al primer cliente de su cola
                System.out.print("Numero de caja (1 a 3): ");
                int caja = leerNumero(sc);

                if (caja < 1 || caja > 3) {
                    System.out.println("Esa caja no existe.");
                } else if (cajas.get(caja - 1).isEmpty()) {
                    System.out.println("La caja " + caja + " no tiene clientes.");
                } else {
                    Cliente cliente = cajas.get(caja - 1).poll();
                    System.out.println("Cliente " + cliente.numero + " paga en la caja " + caja
                            + " y deja libre el carrito " + cliente.carrito);

                    // El carrito queda disponible
                    carritos.add(cliente.carrito);

                    // Si alguien esperaba carrito, lo recibe ahora
                    if (!esperandoCarrito.isEmpty()) {
                        Cliente quienEspera = esperandoCarrito.poll();
                        quienEspera.carrito = carritos.poll();
                        comprando.add(quienEspera);
                        System.out.println("Cliente " + quienEspera.numero + " deja de esperar y toma el carrito "
                                + quienEspera.carrito);
                    }
                }

            } else if (opcion == 4) {
                // Mostrar como esta todo
                System.out.println("Carritos disponibles: " + carritos.size() + " de 25");
                System.out.print("Clientes esperando carrito:");
                for (Cliente c : esperandoCarrito) {
                    System.out.print(" " + c.numero);
                }
                System.out.println();
                System.out.print("Clientes comprando:");
                for (Cliente c : comprando) {
                    System.out.print(" " + c.numero);
                }
                System.out.println();
                for (int i = 0; i < 3; i++) {
                    System.out.print("Caja " + (i + 1) + ":");
                    for (Cliente c : cajas.get(i)) {
                        System.out.print(" " + c.numero);
                    }
                    System.out.println();
                }

            } else if (opcion != 0) {
                System.out.println("Opcion no valida.");
            }
        }
    }

    // Devuelve la posicion (0, 1 o 2) de la caja que tiene menos gente
    public static int cajaConMenosGente(ArrayList<Queue<Cliente>> cajas) {
        int mejor = 0;
        for (int i = 1; i < cajas.size(); i++) {
            if (cajas.get(i).size() < cajas.get(mejor).size()) {
                mejor = i;
            }
        }
        return mejor;
    }

    // Lee un numero entero; si se escribe otra cosa, lo vuelve a pedir
    public static int leerNumero(Scanner sc) {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Escriba un numero valido: ");
            }
        }
    }
}

// Un cliente tiene un numero y el carrito que esta usando
class Cliente {
    int numero;
    int carrito;

    Cliente(int numero) {
        this.numero = numero;
    }
}