import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Ejercicio95 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = 5;

        ArrayList<Stack<Integer>> pilas = new ArrayList<>();
        for (int k = 0; k < n; k++) {
            pilas.add(new Stack<Integer>());
        }

        System.out.println("Ingrese triples (i j k). Para terminar ingrese i = 0");
        int i = sc.nextInt();
        while (i != 0) {
            int j = sc.nextInt();
            int k = sc.nextInt();

            if (Math.abs(i) > n) {
                System.out.println("La pila " + Math.abs(i) + " no existe");
            } else if (k == -1) {
                pilas.get(Math.abs(i) - 1).clear();     // borrar toda la pila
            } else if (i > 0) {
                pilas.get(i - 1).push(j);               // k = 0: como en el 9.4
            } else {
                boolean borrado = pilas.get(-i - 1).remove(Integer.valueOf(j));
                if (!borrado) {
                    System.out.println("El elemento " + j + " no esta en la pila " + (-i));
                }
            }
            i = sc.nextInt();
        }

        for (int p = 0; p < n; p++) {
            System.out.println("P" + (p + 1) + " = " + pilas.get(p));
        }
    }
}
