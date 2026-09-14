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
        
         // Creo B con el tamaño necesario
        int[] b = new int[contador];

        int pos = 0;

        // Aqui para guardar las posiciones donde aparece x
        for (int i = 0; i < a.length; i++) {

            if (a[i] == x) {
                b[pos] = i + 1;
                pos++;
            }
        }


        // Mostrar arreglo A

        System.out.println("Arreglo A:");

        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }

        // Para mostrar X

        System.out.println("\n\nValor buscado (x): " + x);

        // Mostrar arreglo B
        
        System.out.println("Arreglo B (posiciones donde aparece x):");

        for (int i = 0; i < b.length; i++) {
            System.out.print(b[i] + " ");
        }
        
    }


}

