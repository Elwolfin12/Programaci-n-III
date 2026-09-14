public class Punto13 {
    
  
    public static void main(String[] args) {

        // Arreglo A con numeros aleatorios de 1 a 10

        int[] a = new int[10];

        for (int i = 0; i < a.length; i++) {
            a[i] = (int)(Math.random() * 10) + 1;
        }

        // Calculamos la media
        int suma = 0;

        for (int i = 0; i < a.length; i++) {
            suma += a[i];
        }

        double media = (double) suma / a.length;



          // Cuento cuantos son mayores y menores que la media
        int contarMayores = 0;
        int contarMenores = 0;

        for (int i = 0; i < a.length; i++) {

            if (a[i] > media) {
                contarMayores++;
            }

            if (a[i] < media) {
                contarMenores++;
            }
        }

        int[] mayores = new int[contarMayores];
        int[] menores = new int[contarMenores];

        int ma = 0;
        int men = 0;

        // Guardo los mayores y menores
        for (int i = 0; i < a.length; i++) {

            if (a[i] > media) {
                mayores[ma] = a[i];
                ma++;
            }

            if (a[i] < media) {
                menores[men] = a[i];
                men++;
            }
        }


        // Muestro arreglo A
        System.out.println("Arreglo A:");

        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }

        // Muestro media
        System.out.println("\n\nMedia (promedio): " + media);

        // Muestro mayores
        System.out.println("\nElementos MAYORES que la media:");

        for (int i = 0; i < mayores.length; i++) {
            System.out.print(mayores[i] + " ");
        }

        // Muerstro menores
        System.out.println("\n\nElementos MENORES que la media:");

        for (int i = 0; i < menores.length; i++) {
            System.out.print(menores[i] + " ");
        }

    }

}
