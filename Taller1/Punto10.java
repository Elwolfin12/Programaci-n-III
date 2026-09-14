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


        // Sumo los elementos opuestos
        for (; inicio <= fin; inicio++, fin--) {

            if (inicio == fin) {
                b[pos] = a[inicio];
            }

            if (inicio != fin) {
                b[pos] = a[inicio] + a[fin];
            }

            pos++;
        }

        // Mostrar arreglo A
        System.out.println("Arreglo A:");

        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }

        // Mostrar arreglo B
        System.out.println("\n\nArreglo B (suma de los opuestos):");

        for (int i = 0; i < b.length; i++) {
            System.out.print(b[i] + " ");
        }
    }



}
