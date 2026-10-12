import java.util.Scanner;
import java.util.Stack;

// Pila (Stack - LIFO): el carrito de devoluciones de la biblioteca, el usuario escribe 4 libros y se aplican los metodos vistos en clase.

public class Pilas {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Stack<String> carrito = new Stack<>();

        // Uso empty al inicio esta vacia 
        System.out.println("Carrito vacio" + carrito.empty());

         // Con push agrego libros devueltos 
        System.out.println("\nDEVOLUCION DE LIBROS");
        System.out.print("Libro 1: ");
        carrito.push(sc.nextLine());
        System.out.print("Libro 2: ");
        carrito.push(sc.nextLine());
        System.out.print("Libro 3: ");
        carrito.push(sc.nextLine());
        System.out.print("Libro 4: ");
        carrito.push(sc.nextLine());
        System.out.println("\nCarrito: " + carrito);
        System.out.println("Carrito vacio " + carrito.empty());

        // Uso peek para ver el tope sin sacarlo
        System.out.println("\nLibro de encima: " + carrito.peek());

         // el pop para sacar el tope en este caso 
        System.out.println("Libro ubicado en estante: " + carrito.pop());
        System.out.println("Carrito: " + carrito);

        // Con search la posicion desde el tope 
        System.out.print("\nQue libro desea buscar");
        String buscado = sc.nextLine();
        System.out.println("search('" + buscado + "') = " + carrito.search(buscado)
                + "  (1 = tope, -1 = no esta)");

        sc.close();
     
    }
}