/*
Нахождение наибольшей общей подпоследовательности
*/


import java.util.Random;

public class lcs {
    static Random rnd = new Random();
    public static void main(String[] args) {
        int N = 10; // длина последовательностей
        int M = 14;
        int[][] lcs = new int[N + 1][M + 1];
        int[][] last_i = new int[N + 1][M + 1];
        int[][] last_j = new int[N + 1][M + 1];
        int[][] last_symb = new int[N + 1][M + 1];

        for (int i = 0; i <= N ; i++)
            for (int j = 0; j <= M; j++)
            {
                last_i[i][j] = -1;
                last_j[i][j] = -1;
                last_symb[i][j] = -1;
            }


        int[] x = gen_array(N); //генерация случайной последовательности
        int[] y = gen_array(M);

        System.out.print("x = ");
        printArr1D(x, N); // печать исходных последовательностей
        System.out.print("y = ");
        printArr1D(y, M);

        // printArr2D(lcs, N, M); //вывод таблицы пустой

        for (int i = 1; i <= N; i++) { // сравниваем каждый элемент {x} с каждым элементом {y}
            for (int j = 1; j <= M; j++) {
                if (x[i - 1] == y[j - 1]) { // если значения совпали, 
                    lcs[i][j] = lcs[i - 1][j - 1] + 1; //то укорачиваем последовательность на 1 и фиксируем в таблице
                    last_i[i][j] = i - 1;
                    last_j[i][j] = j - 1;
                    last_symb[i][j] = x[i - 1];
                } else {
                    if (lcs[i - 1][j] >  lcs[i][j - 1]) {
                        lcs[i][j] = lcs[i - 1][j];
                        last_i[i][j] = i - 1;
                        last_j[i][j] = j;
                    } else {
                        lcs[i][j] = lcs[i][j - 1];
                        last_i[i][j] = i;
                        last_j[i][j] = j - 1;
                    }
                }
            }
        }
        // int[] subSeq = new int[lcs[N][M]];
        // int cur = last_symb[N][M];
        // // i = 
        // while (last_i[i] != -1) {

        // }
        // printArr2D(lcs, N, M); // вывод таблицы заполненной
        System.out.println("Длина НОП = " + lcs[N][M]); // печать длины НОП
    }

    public static void printArr1D(int[] arr, int length) {
        System.out.print("{ ");
        for (int i = 0; i < length; i++)
            System.out.print(arr[i] + " ");
        System.out.println("}");
    }

    public static int max(int a, int b) {
        if (a > b) 
            return a;
        else 
            return b; 
    }

    public static void printArr2D(int[][] arr, int row, int col) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int[] gen_array(int length) {
        int [] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = rnd.nextInt(10);
        }
        return arr;
    }
}
