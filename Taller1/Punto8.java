public class Punto8 {
 
    public static void main(String[] args) {
 
    // Como el enunciado pide trabajar con 30 numeros, los llenamos de forma aleatoria usando Math.random(), entre min y max osea minimo y maximo
    
        int max = 10;
        int min = 1;
 
        int[] a = new int[30];
 
        for (int i = 0; i < a.length; i++) {
            a[i] = (int) (Math.random() * (max - min + 1)) + min;
        }
 
        int mayor = a[0];
        int menor = a[0];


  // Buscamos el mayor y el menor recorriendo el arreglo

        for (int i = 1; i < a.length; i++) {
            if (a[i] > mayor) {
                mayor = a[i];
            }
            if (a[i] < menor) {
                menor = a[i];
            }
         
         }

  // Ahora contamos cuantas veces se repiten el mayor y el menor

        int vecesMayor = 0;
        int vecesMenor = 0;
 
        for (int i = 0; i < a.length; i++) {
            if (a[i] == mayor) {
                vecesMayor++;
            }
            if (a[i] == menor) {
                vecesMenor++;
            }
        }
 
    //Muestro por consola

        System.out.println("Arreglo de 30 numeros:");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");

        }

        System.out.println("\n\nNumero mayor: " + mayor + " (se repite " + vecesMayor + " veces)");
        System.out.println("Numero menor: " + menor + " (se repite " + vecesMenor + " veces)");

    }

}