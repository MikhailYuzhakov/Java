/*
РЕФАКТОРИНГ (исправленные функции помечены *)
Написать программу вычисления n-ого треугольного числа.
*/
public class N_triangle {
    static int M = 10; //до какого элемента выводим треугльник
    static int [] triang = new int[M]; //массив, хранящий значения треугольника
    public static void main(String[] args) {
        triangle(0, M); //рекурсивно вычисляет числа в треугольнике
        System.out.println(arrToStr(triang, triang.length)); //печать одномерного массива
    }

    //считаем числа
    public static void triangle(int n, int M) {
        triang[n] = (int) (0.5 * n * (n + 1)); //вычисляем по известной формуле
        n = n + 1;
        if (n == M) return; //выходим из рекурсии, если завершили
        else triangle(n, M); //вызываем рекурсивно ту же функцию для дальнейшего расчета 
    }

    // *выполняет только 1 действие - переносит массив длины length в строку
    public static String arrToStr(int[] arr, int length) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (int i = 1; i < length; i++) sb.append(arr[i] + " ");
        sb.append("}");
        return sb.toString();
    }
}
