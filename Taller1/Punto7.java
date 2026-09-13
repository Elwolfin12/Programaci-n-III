public class Punto7 {

   public static void main(String[] args) {

    // Vamos a crear el arreglo A con 10 elementos fijos
        int[] a = {12, 7, 8, 3, 20, 15, 6, 9, 14, 1};

       int contarPares = 0;
       int contarImpares = 0;
 
        for (int i = 0; i < a.length; i++) {
            if (a[i] % 2 == 0) {
                contarPares++;
            } else {
                contarImpares++;
            }
        }
 
        // Creamos los arreglos con el operador new, usando los tamaños calculados
        int[] pares = new int[contarPares];
        int[] impares = new int[contarImpares];
 
        int p = 0;
        int im = 0;
 
        for (int i = 0; i < a.length; i++) {
            if (a[i] % 2 == 0) {
                pares[p] = a[i];
                p++;
            } else {
                impares[im] = a[i];
                im++;
            } 
        }

        //Muestro los resuldados el /n/n es para que queden bien organizados

          System.out.println("Arreglo original A:");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
 
        System.out.println("\n\nArreglo de numeros PARES:");
        for (int i = 0; i < pares.length; i++) {
            System.out.print(pares[i] + " ");
        }
 
        System.out.println("\n\nArreglo de numeros IMPARES:");
        for (int i = 0; i < impares.length; i++) {
            System.out.print(impares[i] + " ");
        }
    }

  }
