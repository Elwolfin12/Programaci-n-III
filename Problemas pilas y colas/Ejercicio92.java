import java.util.Scanner;
import java.util.Stack;

public class Ejercicio92 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese una cadena de la forma X&Y: ");
        String cadena = sc.nextLine();

        if (esDeLaForma(cadena)) {
            System.out.println("SI es de la forma X&Y");
        } else {
            System.out.println("NO es de la forma X&Y");
        }
    }

    // X se apila hasta el '&'; despues cada caracter de Y debe coincidir
    // con el que sale de la pila (la pila invierte el orden de X).
    public static boolean esDeLaForma(String cadena) {
        Stack<Character> pila = new Stack<>();
        int i = 0;

        while (i < cadena.length() && cadena.charAt(i) != '&') {
            pila.push(cadena.charAt(i));
            i++;
        }
        if (i == cadena.length()) {
            return false;              // no hay separador
        }
        i++;                           // saltar el '&'

        while (i < cadena.length()) {
            if (pila.isEmpty() || pila.pop() != cadena.charAt(i)) {
                return false;
            }
            i++;
        }
        return pila.isEmpty();         // si sobran en la pila, Y es mas corta
    }
}