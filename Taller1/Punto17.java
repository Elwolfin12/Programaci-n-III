public class Punto17 {
    
     public static void main(String[] args) {
 
        // Creo la matriz usando el operaddor new y la llenamos directamente convalores fijos siendo una matrz 3x3

        int[][] matriz = new int[3][3];

        matriz[0] = new int[]{1, 2, 3};
        matriz[1] = new int[]{4, 5, 6};
        matriz[2] = new int[]{7, 8, 9};
 
        int filas = matriz.length;
        int columnas = matriz[0].length;


        // Creo los arreglos para guardar la suma de cada fila y de cada columna
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


}
