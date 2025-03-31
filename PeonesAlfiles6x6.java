import java.util.Scanner;

public class PeonesAlfiles6x6 {
    static int N = 6;
    static char[][] tablero = new char[N][N];
    static int maxPiezas = 0;
    static char[][] mejorTablero = new char[N][N];
    static int totalAfiles, totalPeones, colocadosAfiles = 0, colocadosPeones = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese la cantidad de alfiles (m): ");
        totalAfiles = scanner.nextInt();
        System.out.print("Ingrese la cantidad de peones (n): ");
        totalPeones = scanner.nextInt();
        scanner.close();

        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                tablero[i][j] = '.';

        colocarPiezas(0, 0, 0, 0);
        imprimirTablero();
        System.out.println("Máximo de piezas colocadas: " + maxPiezas);
    }

    static void colocarPiezas(int fila, int col, int piezasColocadas, int afilesColocados) {
        if (fila == N) {
            if (piezasColocadas > maxPiezas) {
                maxPiezas = piezasColocadas;
                guardarMejorSolucion();
            }
            return;
        }

        int siguienteFila = (col == N - 1) ? fila + 1 : fila;
        int siguienteCol = (col == N - 1) ? 0 : col + 1;

        if (afilesColocados < totalAfiles && esSeguroAlfil(fila, col)) {
            tablero[fila][col] = 'A';
            colocarPiezas(siguienteFila, siguienteCol, piezasColocadas + 1, afilesColocados + 1);
            tablero[fila][col] = '.';
        }

        if (colocadosPeones < totalPeones && esSeguroPeon(fila, col)) {
            tablero[fila][col] = 'P';
            colocadosPeones++;
            colocarPiezas(siguienteFila, siguienteCol, piezasColocadas + 1, afilesColocados);
            colocadosPeones--;
            tablero[fila][col] = '.';
        }

        colocarPiezas(siguienteFila, siguienteCol, piezasColocadas, afilesColocados);
    }

    static boolean esSeguroAlfil(int fila, int col) {
        for (int i = 1; i < N; i++) {
            if ((fila - i >= 0 && col - i >= 0 && tablero[fila - i][col - i] == 'A') ||
                (fila - i >= 0 && col + i < N && tablero[fila - i][col + i] == 'A') ||
                (fila + i < N && col - i >= 0 && tablero[fila + i][col - i] == 'A') ||
                (fila + i < N && col + i < N && tablero[fila + i][col + i] == 'A')) {
                return false;
            }
        }
        return true;
    }

    static boolean esSeguroPeon(int fila, int col) {
        for (int i = 1; i < N; i++) {
            if ((fila - i >= 0 && col - i >= 0 && tablero[fila - i][col - i] == 'A') ||
                (fila - i >= 0 && col + i < N && tablero[fila - i][col + i] == 'A') ||
                (fila + i < N && col - i >= 0 && tablero[fila + i][col - i] == 'A') ||
                (fila + i < N && col + i < N && tablero[fila + i][col + i] == 'A')) {
                return false;
            }
        }
        return true;
    }

    static void guardarMejorSolucion() {
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                mejorTablero[i][j] = tablero[i][j];
    }

    static void imprimirTablero() {
        for (char[] fila : mejorTablero) {
            for (char celda : fila) {
                System.out.print(celda + " ");
            }
            System.out.println();
        }
    }
}
