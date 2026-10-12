import java.util.Scanner;
import java.util.Stack;

// Pila (Stack - LIFO): el carrito de devoluciones de la biblioteca, el usuario escribe 4 libros y se aplican los metodos vistos en clase.

public class Pilas {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Stack<String> carrito = new Stack<>();

        // Uso empty al inicio esta vacia 
        System.out.println("Carrito vacio" + carrito.empty());