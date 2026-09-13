public class Punto7 {

   public static void main(String[] args) {

    // Vamos a crear el arreglo A con 10 elementos fijos
        int[] a = {12, 7, 8, 3, 20, 15, 6, 9, 14, 1};

        int contarPares = 0;
        int contarImpares = 0;
 
        // Primero contamos cuántos pares e impares hay para poder
        for (int i = 0; i < a.length; i++) {
            contarPares += (a[i] % 2 == 0) ? 1 : 0;
            contarImpares += (a[i] % 2 != 0) ? 1 : 0;
        }

        // Creamos los arreglos con el operador new, usando los tamaños calculados
        int[] pares = new int[contarPares];
        int[] impares = new int[contarImpares];
 
        int p = 0;
        int im = 0;


  }

}
