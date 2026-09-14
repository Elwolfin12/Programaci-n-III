public class Punto24 {

     public static void main(String[] args) {

 
        // Creo la matriz con los 4 cereales (arroz, avena, cebada, trigo) y los 12 meses del año
        String[] cereales = {"Arroz", "Avena", "Cebada", "Trigo"};
        String[] meses = {
            "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
            "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
        };
 
        // Creo la mtriz con la producion (toneladas: filas = cereales, columnas = meses)
        int[][] produccion = {
            {10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10}, // Arroz
            { 5,  5,  5,  5,  5,  5,  5,  5,  5,  5,  5,  5}, // Avena
            { 8,  8,  8,  8,  8,  8,  8,  8,  8,  8,  8, 20}, // Cebada
            {15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15}  // Trigo
        };
 
        // Calculamos el total producido en cada mes, osea sumo los 4 cereales
        int[] totalPorMes = new int[12];
        for (int j = 0; j < 12; j++) {
            int suma = 0;
            for (int i = 0; i < 4; i++) {
                suma += produccion[i][j];
            }
            totalPorMes[j] = suma;
        }
 
        // Este es para el promedio anual de toneladas cosechadas 
        int sumaTotalAnual = 0;
        for (int j = 0; j < 12; j++) {
            sumaTotalAnual += totalPorMes[j];
        }
        double promedioAnual = (double) sumaTotalAnual / 12;
 
        // En este para ver los meses que tuvieron una cosecha superior al promedio anual y para los meses tuvieron una cosecha inferior al promedio anual
        int mesesSuperiores = 0;
        int mesesInferiores = 0;
 
        for (int j = 0; j < 12; j++) {
            if (totalPorMes[j] > promedioAnual) {
                mesesSuperiores++;
            } else if (totalPorMes[j] < promedioAnual) {
                mesesInferiores++;
            }
        }
 
        // Para ver el mes en que se produjo el mayor numero de toneladas
        int mayor = totalPorMes[0];
        int mesMayor = 0;
 
        for (int j = 1; j < 12; j++) {
            if (totalPorMes[j] > mayor) {
                mayor = totalPorMes[j];
                mesMayor = j;
            }
        }
 
        // muestro la matriz de produccion
        System.out.println("Produccion de cereales (toneladas) por mes:\n");
        System.out.print("\t\t");
        for (int j = 0; j < 12; j++) {
            System.out.print(meses[j].substring(0, 3) + "\t");
        }
        System.out.println();
 
        for (int i = 0; i < 4; i++) {
            System.out.print(cereales[i] + "\t");
            for (int j = 0; j < 12; j++) {
                System.out.print(produccion[i][j] + "\t");
            }
            System.out.println();
        }
 
        System.out.println("\nTotal producido por mes:");
        for (int j = 0; j < 12; j++) {
            System.out.println(meses[j] + ": " + totalPorMes[j] + " toneladas");
        }
 
        System.out.println("\na. Promedio anual de toneladas cosechadas: " + promedioAnual);
        System.out.println("b. Meses con cosecha SUPERIOR al promedio: " + mesesSuperiores);
        System.out.println("c. Meses con cosecha INFERIOR al promedio: " + mesesInferiores);
        System.out.println("d. Mes con mayor produccion: " + meses[mesMayor] + " (" + mayor + " toneladas)");
    }
    
}
