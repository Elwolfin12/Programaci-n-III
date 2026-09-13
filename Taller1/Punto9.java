public class Punto9 {
   
     public static void main(String[] args) {
 
        // Arreglo de enteros definido de forma fija 
        int[] a = {2, 5, 2, 8, 2, 3};
 
        // Numero que queremos buscar dentro del arreglo
        int numeroBuscado = 2;
 
        int contador = 0;
 
        for (int i = 0; i < a.length; i++) {
            if (a[i] == numeroBuscado) {
                contador++;
            }
        }

    }

}
