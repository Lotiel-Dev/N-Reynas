public class Caballos6x6 {
    static int N = 6; // Tamaño del tablero
    static char[][] tablero = new char[N][N];
    static char[][] mejorTablero = new char[N][N]; // Para almacenar la mejor solución
    static int maxCaballos = 0; // Máximo de caballos colocados en una solución

    // Movimientos posibles de un caballo en ajedrez
    static int[] movFila = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] movCol = {1, 2, 2, 1, -1, -2, -2, -1};

    public static void main(String[] args) {
        // Inicializar tableros vacíos
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) {
                tablero[i][j] = '.';
                mejorTablero[i][j] = '.';
            }

        // Intentar colocar caballos
        colocarCaballos(0, 0, 0);

        // Imprimir el mejor resultado encontrado
        imprimirTablero();
        System.out.println("Máximo de caballos colocados: " + maxCaballos);
    }

    // Función recursiva para colocar caballos en el tablero
    public static void colocarCaballos(int fila, int col, int caballosColocados) {
        // Si hemos recorrido todo el tablero, actualizar máximo si es necesario
        if (fila == N) {
            if (caballosColocados > maxCaballos) {
                maxCaballos = caballosColocados;
                guardarMejorSolucion();
            }
            return;
        }

        // Calcular siguiente posición en orden fila x columna
        int siguienteFila = (col == N - 1) ? fila + 1 : fila;
        int siguienteColumna = (col == N - 1) ? 0 : col + 1;

        // Intentar colocar un caballo en esta celda
        if (esSeguro(fila, col)) {
            tablero[fila][col] = 'C'; // Colocar caballo
            colocarCaballos(siguienteFila, siguienteColumna, caballosColocados + 1);
            tablero[fila][col] = '.'; // Retroceder (backtracking)
        }

        // Probar siguiente celda sin colocar un caballo
        colocarCaballos(siguienteFila, siguienteColumna, caballosColocados);
    }

    // Verifica si un caballo puede colocarse en (fila, col) sin ser atacado
    public static boolean esSeguro(int fila, int col) {
        for (int i = 0; i < 8; i++) {
            int nuevaFila = fila + movFila[i];
            int nuevaCol = col + movCol[i];
            if (esValido(nuevaFila, nuevaCol) && tablero[nuevaFila][nuevaCol] == 'C')
                return false;
        }
        return true;
    }

    // Guarda la mejor configuración encontrada
    public static void guardarMejorSolucion() {
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                mejorTablero[i][j] = tablero[i][j];
    }

    // Verifica si una posición está dentro del tablero
    public static boolean esValido(int fila, int col) {
        return fila >= 0 && fila < N && col >= 0 && col < N;
    }

    // Imprime el tablero con la mejor configuración encontrada
    public static void imprimirTablero() {
        for (char[] fila : mejorTablero) {
            for (char celda : fila) {
                System.out.print(celda + " ");
            }
            System.out.println();
        }
    }
}
