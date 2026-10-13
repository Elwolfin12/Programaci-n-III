import java.util.Scanner;
import java.util.Stack;

public class Ejercicio92 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese una cadena de la forma X&Y: ");
        String cadena = sc.nextLine();

        if (esDeLaForma(cadena)) {
            System.out.println("SI es de la forma X&Y");
        } else {
            System.out.println("NO es de la forma X&Y");
        }
    


        
    }

}