import java.util.Scanner;
import java.util.Stack;

public class Ejercicio93 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese la expresion: ");
        String expresion = sc.nextLine();

        if (estaEquilibrada(expresion)) {
            System.out.println("La expresion esta equilibrada");
        } else {
            System.out.println("La expresion NO esta equilibrada");
        }
    }

    