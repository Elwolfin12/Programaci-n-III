import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Ejercicio94 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = 5;

        // Se crean las n pilas
        ArrayList<Stack<Integer>> pilas = new ArrayList<>();
        for (int k = 0; k < n; k++) {
            pilas.add(new Stack<Integer>());
        }

 System.out.println("Ingrese pares (i j). Para terminar ingrese i = 0");
        int i = sc.nextInt();
        while (i != 0) {
            int j = sc.nextInt();

            if (Math.abs(i) > n) {
                System.out.println("La pila " + Math.abs(i) + " no existe");
            } else if (i > 0) {
                pilas.get(i - 1).push(j);              // insertar j en Pi
            } else {
                boolean borrado = pilas.get(-i - 1).remove(Integer.valueOf(j));
                if (!borrado) {                         // eliminar j de P|i|
                    System.out.println("El elemento " + j + " no esta en la pila " + (-i));
                }
            }
            
            i = sc.nextInt();
        }

        // Contenido final (de la base a la cima)
        for (int k = 0; k < n; k++) {
            System.out.println("P" + (k + 1) + " = " + pilas.get(k));

        }


    }
}


  