public class Punto22 {
    
   public static void main(String[] args) {
 
        // Doy las Dimensiones de la matriz (filas y columnas)
        int[][] matriz = {
            {5, 12, 8},
            {20, 1, 17},
            {6, 14, 2}
        };
 
        int filas = matriz.length;
        int columnas = matriz[0].length;
 
        // 2. Visualizamos la matriz
        System.out.println("Matriz leida:");
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print(matriz[i][j] + "\t");
            }
            System.out.println();
        }
 
        // 3. Buscamos el mayor y el menor elemento, junto con sus posiciones (fila, columna)
        int mayor = matriz[0][0];
        int menor = matriz[0][0];
        int filaMayor = 0, columnaMayor = 0;
        int filaMenor = 0, columnaMenor = 0;
 
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (matriz[i][j] > mayor) {
                    mayor = matriz[i][j];
                    filaMayor = i;
                    columnaMayor = j;
                }
                if (matriz[i][j] < menor) {
                    menor = matriz[i][j];
                    filaMenor = i;
                    columnaMenor = j;
                }
            }
        }
 
        System.out.println("\nElemento mayor: " + mayor + " (fila " + (filaMayor + 1) + ", columna " + (columnaMayor + 1) + ")");
        System.out.println("Elemento menor: " + menor + " (fila " + (filaMenor + 1) + ", columna " + (columnaMenor + 1) + ")");
    }

}
