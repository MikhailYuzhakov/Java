/*
ДО РЕФАКТОРИНГА
Написать программу вычисления n-ого треугольного числа.
*/
public class N_triangle {
    static int M = 10; //до какого элемента выводим треугльник
    static int [] triang = new int[M]; //массив, хранящий значения треугольника
    public static void main(String[] args) {
        triangle(0, M); //рекурсивно вычисляет числа в треугольнике
        printArr1D(triang, triang.length); //печать одномерного массива
    }

    public static void triangle(int n, int M) {
        triang[n] = (int) (0.5 * n * (n + 1)); //вычисляем по известной формуле
        n = n + 1;
        if (n == M) return; //выходим из рекурсии, если завершили
        else triangle(n, M); //вызываем рекурсивно ту же функцию для дальнейшего расчета 
    }

    // для печать одномерного массива
    public static void printArr1D(int[] arr, int length) {
        System.out.print("{ ");
        for (int i = 1; i < length; i++)
            System.out.print(arr[i] + " ");
        System.out.println("}");
    }
}
