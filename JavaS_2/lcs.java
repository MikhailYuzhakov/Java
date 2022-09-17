/*
ДО РЕФАКТОРИНГА
Нахождение наибольшей общей подпоследовательности (сложность алгоритма О(n*M), перебором O(n^m))
*/

import java.util.Random;

public class lcs {
    static Random rnd = new Random();
    public static void main(String[] args) {
        int N = 100; // длины последовательностей
        int M = 100;
        int[][] lcs = new int[N + 1][M + 1]; //таблица НОП префиксов 

        // int[] x = {5, 9, 3, 8, 2};
        // int[] y = {9, 3, 3, 9, 8};
        int[] x = gen_array(N); //генерация случайной последовательности размера N
        int[] y = gen_array(M); //генерация случайно последовательности размера M

        System.out.print("x = "); // печать исходных последовательностей
        printArr1D(x, N); 
        System.out.print("y = ");
        printArr1D(y, M);

        //создаем таблицу (карту маршрутов для нахождения НОП)
        for (int i = 1; i <= N; i++) // сравниваем каждый элемент {x} с каждым элементом {y}
            for (int j = 1; j <= M; j++)
                //при совпадении элементов - укорачиваем обе последовательности на 1 элемент
                if (x[i - 1] == y[j - 1]) lcs[i][j] = lcs[i - 1][j - 1] + 1;                
                else {
                    //при несовпадении элементов - исключаем элемент одной из последовательностей и укорачиваем её
                    if (lcs[i - 1][j] > lcs[i][j - 1]) lcs[i][j] = lcs[i - 1][j];  
                    else lcs[i][j] = lcs[i][j - 1];
                }
        //System.out.println("LCS:");
        //printArr2D(lcs, N + 1, M + 1); // вывод таблицы заполненной
        System.out.println("Длина НОП = " + lcs[N][M]); // печать длины НОП
        print_lcs(lcs, x, y, N, M);
    }

    //функция для печати одномерного массива
    public static void printArr1D(int[] arr, int length) {
        System.out.print("{ ");
        for (int i = 0; i < length; i++)
            System.out.print(arr[i] + " ");
        System.out.println("}");
    }

    //функция для печати двумерного массива
    public static void printArr2D(int[][] arr, int row, int col) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    //функция для генерации случайного массива длины length
    public static int[] gen_array(int length) {
        int [] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = rnd.nextInt(10);
        }
        return arr;
    }

    //функция для вывода НОП
    public static void print_lcs(int[][] lcs, int[] x, int[] y, int N, int M) {
        int k = 0;
        int ans[] = new int[lcs[N][M]]; //создаем массив для хранения ответа длинной равной НОП
        while (N > 0 && M > 0) { //пока не добрались до границы таблицы
            if (x[N - 1] == y[M - 1]) { //если элементы равны - записываем в ответ
                ans[k] = x[N - 1];
                k++;
                N--;
                M--;
            } else 
                if (lcs[N - 1][M] == lcs[N][M]) { //двигаемся в ту ячейку где префикс равен текущему (совпадений не было)
                    N--;
                } else {
                    M--;
                }
        }
        System.out.print("НОП: { ");
        for (N = ans.length - 1; N >= 0; N--) System.out.print(ans[N] + " "); //выводим ответ в обратном порядке
        System.out.println("}");
    }
}
