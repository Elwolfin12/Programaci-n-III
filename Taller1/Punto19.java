public class Punto19 {
    

     public static void main(String[] args) {
 
        // Defino las dimensiones de la matriz (n filas, m columnas)
        int n = 5;
        int m = 5;
 
        // Creo la matriz con el operador new
        int[][] matriz = new int[n][m];
 
        // Al crear la matriz con new, todos los elementos ya quedan en 0 por defecto y solo necesito poner 1 en la diagonal principal (donde fila == columna).
        for (int i = 0; i < n && i < m; i++) {
            matriz[i][i] = 1;
        }
    


        System.out.println("Matriz " + n + " x " + m + " con la diagonal principal en 1:\n");
 
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

            System.out.print(matriz[i][j] + " ");
            }

            System.out.println();
        }


    }


}