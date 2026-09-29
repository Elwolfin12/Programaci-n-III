import java.util.Scanner;

// Clase que se encarga de vender las entradas
public class Ventas {

    // La matriz de funciones se recibe desde Main
    // funciones[0][0] = sala 1, franja 1 ... funciones[2][2] = sala 3, franja 3
    private Funciones[][] funciones;

    public Ventas(Funciones[][] funciones) {
        this.funciones = funciones;
    }

    // Menu de ventas: se repite hasta que el usuario escriba 0
    public void menuVentas(Scanner entrada) {
        int numSala = -1;

        while (numSala != 0) {
            System.out.println("\n======= MENU DE VENTAS =======");
            System.out.print("Sala (1-3) o 0 para volver: ");
            numSala = Main.leerEntero();

            if (numSala < 0 || numSala > 3) {
                System.out.println("Sala no valida.");
            } else if (numSala != 0) {

                System.out.println("Franjas: 1) " + Funciones.obtenerHorario(1) + "  2) " + Funciones.obtenerHorario(2)
                        + "  3) " + Funciones.obtenerHorario(3));
                System.out.print("Franja (1-3): ");
                int franja = Main.leerEntero();

                if (franja < 1 || franja > 3) {
                    System.out.println("Franja no valida.");
                } else {
                    // Se resta 1 porque la matriz empieza en la posicion 0
                    Funciones funcion = funciones[numSala - 1][franja - 1];

                    if (funcion == null) {
                        System.out.println("Esa funcion no tiene pelicula asignada.");
                    } else {
                        venderEntradas(entrada, funcion);
                    }
                }
            }
        }
    }

    // Vende sillas de una funcion, de una en una, hasta que el usuario diga que no
    private void venderEntradas(Scanner entrada, Funciones funcion) {
        sala laSala = funcion.getObjetoSala();

        int generales = 0;        // boletas General (o 3D) compradas
        int preferenciales = 0;   // boletas Preferencial compradas
        int total = 0;            // total a pagar
        String seguir = "s";

        while (seguir.equals("s")) {
            System.out.println();
            funcion.mostrarInfo();
            laSala.mostrarSala();

            int disponibles = laSala.contarDisponibles();
            System.out.println("Sillas disponibles: " + disponibles);

            if (disponibles == 0) {
                System.out.println("Funcion agotada, la sala esta llena!");
                seguir = "n";
            } else {
                System.out.print("Letra de la fila (ej: A): ");
                String letra = entrada.nextLine().toUpperCase();
                System.out.print("Numero de la silla (ej: 3): ");
                int numero = Main.leerEntero();

                int fila = laSala.buscarFila(letra);

                if (fila == -1 || !laSala.sillaExiste(fila, numero)) {
                    // La silla no existe en esta sala
                    System.out.println("[!] La silla " + letra + numero + " no existe.");
                } else if (!laSala.estaLibre(fila, numero)) {
                    // La silla ya fue comprada
                    System.out.println("[!] La silla " + letra + numero + " ya esta ocupada.");
                } else {
                    // La silla esta libre: se compra
                    laSala.ocuparSilla(fila, numero);
                    total = total + laSala.getPrecio(fila);

                    if (laSala.esPreferencial(fila)) {
                        preferenciales++;
                    } else {
                        generales++;
                    }
                    System.out.println("Silla " + letra + numero + " comprada.");
                }

                System.out.print("Desea comprar otra silla? (s/n): ");
                seguir = entrada.nextLine().toLowerCase();
            }
        }

        // Resumen final de la compra (solo si compro algo)
        if (generales + preferenciales > 0) {
            System.out.println("\n----- RESUMEN DE LA COMPRA -----");
            if (generales > 0) {
                System.out.println(generales + " boleta(s) General/3D x $" + laSala.getPrecio(0));
            }
            if (preferenciales > 0) {
                System.out.println(preferenciales + " boleta(s) Preferencial x $" + laSala.getPrecio(6));
            }
            System.out.println("TOTAL A PAGAR: $" + total);
        }
    }
}