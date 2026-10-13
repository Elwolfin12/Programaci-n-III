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

    


}