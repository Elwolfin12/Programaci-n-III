public class Punto9 {   

   public static void main(String[] args) {
 
        // Arreglo de enteros definido de forma fija
        int[] a = {2, 5, 2, 8, 2, 3};
 
        // Numero que queremos buscar dentro del arreglo se pone el algoritmo
        int numeroBuscado = 2;
        int contador = 0;
 
        for (int i = 0; i < a.length; i++) {
            if (a[i] == numeroBuscado) {
                contador++;
            }
        }
 
        System.out.println("Arreglo:");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
 
        System.out.println("\n\nEl numero " + numeroBuscado + " se encuentra " + contador + " veces dentro del arreglo.");
    }
}
 