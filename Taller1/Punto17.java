public class Punto17 {
    
     public static void main(String[] args) {
 
        // Creo la matriz con valores fijos con dos dimensiones (filas y columnas)

        int[][] matriz = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
 
        int filas = matriz.length;
        int columnas = matriz[0].length;
 
        // creo los arreglos para guardar la suma de cada fila y la suma de cada columna
        int[] sumaFilas = new int[filas];
 
        int[] sumaColumnas = new int[columnas];
 
        System.out.println("Matriz:");
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print(matriz[i][j] + "\t");
                sumaFilas[i] += matriz[i][j];
                sumaColumnas[j] += matriz[i][j];
            }
            System.out.println();
        }
 
        System.out.println("\nSuma de cada fila:");
        for (int i = 0; i < sumaFilas.length; i++) {
            System.out.println("Fila " + (i + 1) + ": " + sumaFilas[i]);
        }
 
        System.out.println("\nSuma de cada columna:");
        for (int j = 0; j < sumaColumnas.length; j++) {
            System.out.println("Columna " + (j + 1) + ": " + sumaColumnas[j]);
        }
    }



}
