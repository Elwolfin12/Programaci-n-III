import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// Cola (Queue - FIFO): reservas de un libro en la biblioteca, el usuario escribe los 4 nombres y se aplican los métodos vistos en clase.

public class Colas {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Queue<String> reservas = new LinkedList<>();

        // En este lado para insertar con estos add y offer 
        System.out.println("RESERVAS DEL LIBRO 'Noches blancas'");
        System.out.print("Nombre del lector 1: ");
        reservas.add(sc.nextLine());
        System.out.print("Nombre del lector 2: ");
        reservas.add(sc.nextLine());
        System.out.print("Nombre del lector 3: ");
        reservas.offer(sc.nextLine());
        System.out.print("Nombre del lector 4: ");
        reservas.offer(sc.nextLine());
        System.out.println("\nCola de reservas: " + reservas);