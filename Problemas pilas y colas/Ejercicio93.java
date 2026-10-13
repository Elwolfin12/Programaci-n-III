import java.util.Scanner;
import java.util.Stack;

public class Ejercicio93 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese la expresion: ");
        String expresion = sc.nextLine();

        if (estaEquilibrada(expresion)) {
            System.out.println("La expresion esta equilibrada");
        } else {
            System.out.println("La expresion NO esta equilibrada");
        }
    }

     // Cada apertura se apila; cada cierre debe coincidir con la cima.
    public static boolean estaEquilibrada(String expresion) {
        Stack<Character> pila = new Stack<>();

        for (int i = 0; i < expresion.length(); i++) {
            char c = expresion.charAt(i);

            if (c == '(' || c == '{' || c == '[') {
                pila.push(c);
            } else if (c == ')' || c == '}' || c == ']') {
                if (pila.isEmpty()) {
                    return false;      // cierre sin apertura
                }
                char apertura = pila.pop();
                if ((c == ')' && apertura != '(')
                        || (c == '}' && apertura != '{')
                        || (c == ']' && apertura != '[')) {
                    return false;      // no coincide
                }
            }
        }
        return pila.isEmpty();         // si sobran, falta cerrar alguno
    }
}
