public class Punto21 {


    public static void main(String[] args) {
 
        // Matriz M x M 
        int m = 4;
        int[][] matriz = {
            {3, 8, 5, 2},
            {7, 4, 9, 6},
            {10, 1, 12, 3},
            {15, 8, 20, 9}
        };
 
        // Vector b para esto se calculan todas las sumas de pares por fila 
        int[] b = new int[m];
 
        for (int i = 0; i < m; i++) {
            int sumaPares = 0;
            for (int j = 0; j < m; j++) {
                if (matriz[i][j] % 2 == 0) {
                    sumaPares += matriz[i][j];
                }
            }

            b[i] = sumaPares;
        }
 
        // Ahora muestro la matriz completa
        System.out.println("Matriz " + m + "x" + m + ":");

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(matriz[i][j] + "\t");
            }
            System.out.println();
        }
 
        // Ya aqui muestro el vector B ya calculado
        System.out.println("\nVector B:");

        for (int i = 0; i < b.length; i++) {
            System.out.println("Suma de pares de la fila " + (i + 1) + ": " + b[i]);
        }

    }

    
}
