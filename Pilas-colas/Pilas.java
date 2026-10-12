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

     
    }
}