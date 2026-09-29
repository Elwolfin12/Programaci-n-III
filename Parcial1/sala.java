public class sala {

    private int numeroSala;
    private int filas;            // 6 filas en la sala 3, 8 filas en las salas 1 y 2
    private String[][] asientos;  // la matriz
    private String letras = "ABCDEFGH";

    // Constructor: crea la matriz y la llena
    public sala(int numeroSala) {
        this.numeroSala = numeroSala;

        if (numeroSala == 3) {
            filas = 6;
        } else {
            filas = 8;
        }

        asientos = new String[filas][12];

        // Recorremos toda la matriz con dos for
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < 12; j++) {
                // Las filas A-F (0 a 5) tienen 12 sillas.
                // Las filas G y H solo tienen 9 (columnas 0 a 8).
                if (i < 6 || j < 9) {
                    asientos[i][j] = "_";
                } else {
                    asientos[i][j] = " ";
                }
            }
        }
    }

    // Recibe la letra que escribio el usuario y devuelve en que fila esta.
    // Si la letra no existe en esta sala devuelve -1.
    public int buscarFila(String letra) {
        if (letra.length() != 1) {
            return -1;
        }
        for (int i = 0; i < filas; i++) {
            if (letras.charAt(i) == letra.charAt(0)) {
                return i;
            }
        }
        return -1;
    }

    // Revisa que el numero de silla exista en esa fila
    public boolean sillaExiste(int fila, int numero) {
        if (numero < 1 || numero > 12) {
            return false;
        }
        // En las filas G y H (fila 6 y 7) solo hay 9 sillas
        if (fila >= 6 && numero > 9) {
            return false;
        }
        return true;
    }

    // true si la silla esta libre (se resta 1 porque la matriz empieza en 0)
    public boolean estaLibre(int fila, int numero) {
        return asientos[fila][numero - 1].equals("_");
    }

    // Marca la silla como ocupada
    public void ocuparSilla(int fila, int numero) {
        asientos[fila][numero - 1] = "X";
    }

    // Las filas G y H (6 y 7) son preferenciales
    public boolean esPreferencial(int fila) {
        return fila >= 6;
    }

    // Precio de la silla segun la sala y la fila
    public int getPrecio(int fila) {
        if (numeroSala == 3) {
            return 10000;
        } else if (fila >= 6) {
            return 12000;
        } else {
            return 8000;
        }
    }

    // Cuenta las sillas que siguen libres
    public int contarDisponibles() {
        int contador = 0;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < 12; j++) {
                if (asientos[i][j].equals("_")) {
                    contador++;
                }
            }
        }
        return contador;
    }

    // Dibuja la sala en la consola
    public void mostrarSala() {
        System.out.println("SALA " + numeroSala + "   (_ = libre, X = ocupada)");

        // Fila de arriba con los numeros de las sillas
        System.out.print("   ");
        for (int j = 1; j <= 12; j++) {
            if (j < 10) {
                System.out.print(j + "  ");
            } else {
                System.out.print(j + " ");
            }
        }
        System.out.println();

        // Se imprime de la ultima fila a la primera: arriba queda el preferencial
        for (int i = filas - 1; i >= 0; i--) {

            // Linea que separa el preferencial del general
            if (i == 5 && filas == 8) {
                System.out.println("   ------------------------------------");
            }

            System.out.print(letras.charAt(i) + "  ");
            for (int j = 0; j < 12; j++) {
                System.out.print(asientos[i][j] + "  ");
            }
            System.out.println();
        }
        System.out.println("        ====== PANTALLA ======");
    }
}