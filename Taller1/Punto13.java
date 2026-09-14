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


}
