public class Punto10 {
    
    public static void main(String[] args) {

        // Creo el arreglo A con numeros aleatorios de 1 a 10
        int[] a = new int[5];

        for (int i = 0; i < a.length; i++) {
            a[i] = (int)(Math.random() * 10) + 1;
        }


        int n = a.length;


        // Calculamos el tamaño del arreglo B
        int tamanoB = n / 2;

        if (n % 2 != 0) {
            tamanoB++;
        }

        int[] b = new int[tamanoB];

        int inicio = 0;
        int fin = n - 1;
        int pos = 0;






}
