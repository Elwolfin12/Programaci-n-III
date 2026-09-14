public class Punto18 {

public static void main(String[] args) {
 
        // Creo la tabla (matriz) usando el operador new: 10 filas x 10 columnas
        int[][] tabla = new int[10][10];
 
        // lleno la tabla de multiplicar: tabla[i][j] = (i+1) * (j+1)

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                tabla[i][j] = (i + 1) * (j + 1);
            }
        }
 
        // Imprimimos la tabla de multiplicar completa
        System.out.println("Tabla de multiplicar del 1 al 10:\n");
 
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(tabla[i][j] + "\t");
            }
            System.out.println();
        }


    }


}