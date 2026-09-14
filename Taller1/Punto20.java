public class Punto20 {

      public static void main(String[] args) {
 
        // Creo una matrz 10x10 con el operador new
        int[][] matriz = new int[10][10];
 
        // Al crear la matriz con new, todos los elementos ya quedan en 0 como lo hice en el 19 y asi formando la diagonal principal Solo necesitamos poner 1 por debajo de la diagonal (donde j < i).
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < i; j++) {
                matriz[i][j] = 1;
            }
        }
 
        System.out.println("Matriz 10x10 con ceros en la diagonal principal hacia arriba:\n");
 
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }
    
}
