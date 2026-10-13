import java.util.Scanner;
import java.util.Stack;

public class Ejercicio96 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Escriba una frase por linea (linea vacia para terminar):");

        String frase = sc.nextLine();
        while (!frase.isEmpty()) {
            if (esPalindromo(frase)) {
                System.out.println("SI es palindromo");
            } else {
                System.out.println("NO es palindromo");
            }
            frase = sc.nextLine();
        }
    }

    // Cada caracter va a la pila y al final de la lista circular.
    // Luego se saca uno de la pila (el ultimo) y el primero de la lista y se comparan.
    public static boolean esPalindromo(String frase) {
        String limpia = limpiar(frase);

        Stack<Character> pila = new Stack<>();
        ListaCircular lista = new ListaCircular();

        for (int i = 0; i < limpia.length(); i++) {
            pila.push(limpia.charAt(i));
            lista.insertarFinal(limpia.charAt(i));
        }

        while (!pila.isEmpty()) {
            if (pila.pop() != lista.extraerPrimero()) {
                return false;
            }
        }
        return true;
    }

 // Deja la frase en minusculas, sin tildes, sin espacios y sin signos
    public static String limpiar(String frase) {
        String resultado = "";
        frase = frase.toLowerCase();

        for (int i = 0; i < frase.length(); i++) {
            char c = frase.charAt(i);

            // cambiar vocales con tilde por la vocal normal
            if (c == 'á') {
                c = 'a';
            } else if (c == 'é') {
                c = 'e';
            } else if (c == 'í') {
                c = 'i';
            } else if (c == 'ó') {
                c = 'o';
            } else if (c == 'ú' || c == 'ü') {
                c = 'u';
            }

            // quedarse solo con letras y numeros
            if (Character.isLetterOrDigit(c)) {
                resultado = resultado + c;
            }
        }
        return resultado;
    }

    // Lista enlazada circular: se guarda el ultimo nodo, el primero es ultimo.siguiente
    static class ListaCircular {

        static class Nodo {
            char dato;
            Nodo siguiente;

            Nodo(char dato) {
                this.dato = dato;
            }
        }

        Nodo ultimo = null;

        void insertarFinal(char c) {
            Nodo nuevo = new Nodo(c);
            if (ultimo == null) {
                nuevo.siguiente = nuevo;
            } else {
                nuevo.siguiente = ultimo.siguiente;
                ultimo.siguiente = nuevo;
            }
            ultimo = nuevo;
        }

        char extraerPrimero() {
            Nodo primero = ultimo.siguiente;
            if (primero == ultimo) {
                ultimo = null;
            } else {
                ultimo.siguiente = primero.siguiente;
            }
            return primero.dato;
        }
    }
}
