/*
РЕФАКТОРИНГ (исправленные функции помечены *)
Нахождение наибольшей общей подпоследовательности (НОП) (сложность алгоритма О(n*M))
*/

import java.util.Random;

public class lcs {
    static Random rnd = new Random();
    public static void main(String[] args) {
        int N = 100; // длины последовательностей
        int M = 100;
        int[][] lcs = new int[N + 1][M + 1]; //таблица НОП префиксов 

        // int[] x = {5, 9, 3, 8, 2}; //задаем статичный массив
        // int[] y = {9, 3, 3, 9, 8};
        int[] x = gen_array(N); //генерация случайной последовательности размера N
        int[] y = gen_array(M); //генерация случайно последовательности размера M

        System.out.println("x = " + arrToStr1D(x, N)); // печать исходных последовательностей
        System.out.println("y = " + arrToStr1D(y, M));

        lcs = find_ways_weigth(lcs, x, y); //считаем все маршруты и их веса
        
        // System.out.println("LCS:\n" + printArr2D(lcs, N + 1, M + 1)); // вывод таблицы весов заполненной
        System.out.println("Длина НОП = " + lcs[N][M]); // печать длины НОП
        System.out.println(print_lcs(lcs, x, y, N, M)); // вывод НОП
    }

    // *выполняет только 1 действие - переносит массив длины length в строку
    public static String arrToStr1D(int[] arr, int length) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (int i = 1; i < length; i++) sb.append(arr[i] + " ");
        sb.append("}");
        return sb.toString();
    }

    // *заполним таблицу с весами маршрутов и вернем её
    public static int[][] find_ways_weigth(int[][] lcs, int[] x, int[] y) {
        //создаем таблицу (карту маршрутов для нахождения НОП)
        for (int i = 1; i <= x.length; i++) // сравниваем каждый элемент {x} с каждым элементом {y}
            for (int j = 1; j <= y.length; j++)
                //при совпадении элементов - укорачиваем обе последовательности на 1 элемент
                if (x[i - 1] == y[j - 1]) lcs[i][j] = lcs[i - 1][j - 1] + 1;                
                else {
                    //при несовпадении элементов - исключаем элемент одной из последовательностей и укорачиваем её
                    if (lcs[i - 1][j] > lcs[i][j - 1]) lcs[i][j] = lcs[i - 1][j];  
                    else lcs[i][j] = lcs[i][j - 1];
                }
        return lcs;
    }

    // *функция для печати двумерного массива
    public static String printArr2D(int[][] arr, int row, int col) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) sb.append(arr[i][j] + " ");
            sb.append("\n");
        }
        return sb.toString();
    }

    //функция для генерации случайного массива длины length
    public static int[] gen_array(int length) {
        int [] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = rnd.nextInt(10);
        }
        return arr;
    }

    // *функция для вывода НОП (печатает в строку)
    public static String print_lcs(int[][] lcs, int[] x, int[] y, int N, int M) {
        StringBuilder sb = new StringBuilder();
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
        sb.append("НОП: { ");
        for (N = ans.length - 1; N >= 0; N--) sb.append(ans[N] + " "); //выводим ответ в обратном порядке
        sb.append("}");
        return sb.toString();
    }
}
