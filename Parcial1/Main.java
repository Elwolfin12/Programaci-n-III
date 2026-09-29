import java.util.Scanner;
 
public class Main {
 
    static Scanner entrada = new Scanner(System.in);
    static Peliculas[] peliculas = new Peliculas[15]; // arreglo de peliculas (maximo 15)
    static int cPeliculas = 0;                        // cuantas peliculas hay guardadas
    static Funciones[][] funciones = new Funciones[3][3]; // matriz [sala][franja]
 
    public static void main(String[] args) {
 
        Ventas ventas = new Ventas(funciones);
        int opcion;
 
        // El menu se repite hasta que el usuario elija 4 (salir)
        do {
            System.out.println("\n===========CINEMASTAR===============");
            System.out.println("1. menu de creacion de peliculas");
            System.out.println("2. menu de funciones");
            System.out.println("3. menu de entradas");
            System.out.println("4. salir");
            System.out.print("ingrese una opcion: ");
            opcion = leerEntero();
 
            switch (opcion) {
                case 1:
                    menuPeliculas();
                    break;
                case 2:
                    menuFunciones();
                    break;
                case 3:
                    ventas.menuVentas(entrada);
                    break;
                case 4:
                    System.out.println("Hasta luego!");
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }
        } while (opcion != 4);
 
        entrada.close();
    }
 
    // Lee un numero entero. Si el usuario escribe letras, avisa y vuelve a pedir
    // (asi el programa no se cierra por error recomendacion de la ia)
    public static int leerEntero() {
        while (!entrada.hasNextInt()) {
            System.out.println("Debe escribir un numero.");
            entrada.next();
        }
        int numero = entrada.nextInt();
        entrada.nextLine(); // limpia el salto de linea que queda
        return numero;
    }
 
    // apartado de peliculas y su menu 
 
    public static void menuPeliculas() {
        int op = 0;
 
        while (op != 3) {
            System.out.println("\n--- CREACION DE PELICULAS ---");
            System.out.println("1. ver peliculas");
            System.out.println("2. anadir pelicula");
            System.out.println("3. volver");
            System.out.print("ingrese una opcion: ");
            op = leerEntero();
 
            if (op == 1) {
                mostrarPeliculas();
            } else if (op == 2) {
                anadirPelicula();
            } else if (op != 3) {
                System.out.println("Opcion no valida.");
            }
        }
    }
 
    public static void mostrarPeliculas() {
        if (cPeliculas == 0) {
            System.out.println("No hay peliculas registradas.");
        } else {
            for (int i = 0; i < cPeliculas; i++) {
                System.out.print((i + 1) + ". ");
                peliculas[i].mostrarInfo();
            }
        }
    }
 
    public static void anadirPelicula() {
        // Si el arreglo ya esta lleno no se puede guardar otra
        if (cPeliculas == peliculas.length) {
            System.out.println("El repertorio esta lleno.");
            return;
        }
 
        System.out.print("Nombre: ");
        String nombre = entrada.nextLine();
        System.out.print("Idioma: ");
        String idioma = entrada.nextLine();
 
        // Se repite hasta que el usuario escriba 1 o 2
        int t = 0;
        while (t != 1 && t != 2) {
            System.out.print("Tipo (1 = 35mm, 2 = 3D): ");
            t = leerEntero();
        }
        String tipo;
        if (t == 1) {
            tipo = "35mm";
        } else {
            tipo = "3D";
        }
 
        // Se repite hasta que la duracion sea mayor que 0
        int duracion = 0;
        while (duracion <= 0) {
            System.out.print("Duracion en minutos: ");
            duracion = leerEntero();
        }
 
        // Se crea la pelicula y se guarda en el arreglo
        peliculas[cPeliculas] = new Peliculas(nombre, idioma, tipo, duracion);
        cPeliculas++;
        System.out.println("Pelicula registrada.");
    }
 
    // Funciones
 
    public static void menuFunciones() {
        int op = 0;
 
        while (op != 3) {
            System.out.println("\n--- ASIGNACION DE FUNCIONES ---");
            System.out.println("1. ver funciones");
            System.out.println("2. asignar pelicula a una funcion");
            System.out.println("3. volver");
            System.out.print("ingrese una opcion: ");
            op = leerEntero();
 
            if (op == 1) {
                mostrarFunciones();
            } else if (op == 2) {
                asignarFuncion();
            } else if (op != 3) {
                System.out.println("Opcion no valida.");
            }
        }
    }
 
    // Recorre la matriz de funciones: i = sala, j = franja
    public static void mostrarFunciones() {
        for (int i = 0; i < 3; i++) {
            System.out.println("\nSALA " + (i + 1));
            for (int j = 0; j < 3; j++) {
                System.out.print("  " + (j + 1) + ") " + Funciones.obtenerHorario(j + 1) + " -> ");
                if (funciones[i][j] == null) {
                    System.out.println("SIN ASIGNAR");
                } else {
                    System.out.println(funciones[i][j].getPelicula().getNombre() + " ("
                            + funciones[i][j].getPelicula().getTipo() + ") | sillas disponibles: "
                            + funciones[i][j].getDisponibles());
                }
            }
        }
    }
 
    // Sala 3: solo peliculas 3D.  Salas 1 y 2: todas menos las 3D.
    public static boolean esCompatible(int numSala, Peliculas p) {
        if (numSala == 3) {
            return p.esTresD();
        } else {
            return !p.esTresD();
        }
    }
 
    public static void asignarFuncion() {
        if (cPeliculas == 0) {
            System.out.println("Primero registre peliculas.");
            return;
        }
 
        // Pedir la sala (repite hasta que sea 1, 2 o 3)
        int numSala = 0;
        while (numSala < 1 || numSala > 3) {
            System.out.print("Sala (1-3): ");
            numSala = leerEntero();
        }
 
        // Pedir la franja (repite hasta que sea 1, 2 o 3)
        System.out.println("Franjas: 1) " + Funciones.obtenerHorario(1) + "  2) " + Funciones.obtenerHorario(2)
                + "  3) " + Funciones.obtenerHorario(3));
        int franja = 0;
        while (franja < 1 || franja > 3) {
            System.out.print("Franja (1-3): ");
            franja = leerEntero();
        }
 
        // Si esa posicion de la matriz ya tiene una funcion, la sala esta ocupada en esa franja
        if (funciones[numSala - 1][franja - 1] != null) {
            System.out.println("[!] Esa sala ya tiene una pelicula en esa franja.");
            return;
        }
 
        // Muestra solo las peliculas que se pueden pasar en esa sala
        System.out.println("Peliculas que se pueden proyectar en la sala " + numSala + ":");
        int cuantas = 0;
        for (int i = 0; i < cPeliculas; i++) {
            if (esCompatible(numSala, peliculas[i])) {
                System.out.print((i + 1) + ". ");
                peliculas[i].mostrarInfo();
                cuantas++;
            }
        }
        if (cuantas == 0) {
            System.out.println("No hay peliculas compatibles (sala 3 solo 3D, salas 1 y 2 sin 3D).");
            return;
        }
 
        System.out.print("Numero de la pelicula: ");
        int eleccion = leerEntero();
 
        if (eleccion < 1 || eleccion > cPeliculas || !esCompatible(numSala, peliculas[eleccion - 1])) {
            System.out.println("[!] Seleccion no valida.");
            return;
        }
 
        // Se crea la funcion y se guarda en la matriz
        funciones[numSala - 1][franja - 1] = new Funciones(peliculas[eleccion - 1], numSala,
                Funciones.obtenerHorario(franja));
        System.out.println("Funcion asignada.");
    }
}
 