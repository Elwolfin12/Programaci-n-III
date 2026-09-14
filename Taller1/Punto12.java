public class Punto12 {

      public static void main(String[] args) {

        // Arreglo A con numeros aleatorios de 1 a 5
        int[] a = new int[10];

        for (int i = 0; i < a.length; i++) {
            a[i] = (int)(Math.random() * 5) + 1;
        }

        // Valor que estamos buscando
        int x = (int)(Math.random() * 5) + 1;

        // Contamos cuantas veces aparece x
        int contador = 0;

        for (int i = 0; i < a.length; i++) {

            if (a[i] == x) {
                contador++;
            }
        }

}
