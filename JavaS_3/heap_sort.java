/*
 * отсортировать массив с помощью heapsort (пираимдальная сортировка)
 * дочерний элемент всегда меньше родительского (используем бинарное дерево)
 */

import java.util.Random;
import java.util.TreeSet;

public class heap_sort {
    public static void main(String[] args) {

        TreeSet<Integer> ts = new TreeSet<Integer>(); //встроенная коллекция Java дерево, сортирует по возрастанию
        int[] array = gen_array(10);
        for (int item : array) { //записываем в коллекцию все значения массива
            ts.add(item);
        }
        System.out.println("Заданный массив: " + print_arr(array)); //изначальный массив
        heapSort(array); //сортируем массив с помощью heap sort
        System.out.println("Отсортированный массив: " + print_arr(array)); //отсортированный массив
        System.out.println("Отсортированный через TreeSet массив: " + ts); //отсортированный массив с помощью коллекции 
        //TreeSet не хранит повторяющиеся элементы
    }

    //случайно генерирует массив
    public static int[] gen_array(int N) {
        Random random = new Random();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = random.nextInt(-100, 100);
        }
        return arr;
    }

    //печатает массив
    public static StringBuilder print_arr(int[] arr) {
        StringBuilder str = new StringBuilder();
        for (int item : arr) {
            str.append(item);
            str.append(" ");
       } 
       return str;
    }

    //пирамидальная сортировка массива
    public static void heapSort(int[] array) {
        int n = array.length;

        for (int i = n / 2 - 1; i >= 0; i--) { //создаем дерево
            heapify(array, i, n); //ставит больший элемент на место родительского и обновляет дерево
        }

        //с конца восстанавливаем массив согласно дереву (максимальный элемент - корень дерева - в конец массива)
        for (int i = n - 1; i >= 0; i--) {
           int temp = array[i];
           array[i] = array[0];
           array[0] = temp;
           
           heapify(array, 0, i); //обновляем дерево
        }     
    }

    //обновление бинарного дерева
    public static void heapify(int[] array, int i, int n) {
        int l = i * 2 + 1; //левый ребенок
        int r = i * 2 + 2; //правый ребенок
        int max = i; //полагаем, что по умолчанию родительский элемент - максимальный

        //если индекс левого "ребенка" не вылезает на пределы массива и он больше родительского, то
        if (l < n && array[l] > array[max]) {
            max = l; //запоминаем индекс максмального элемента тройки
        }
        //аналогично
        if (r < n && array[r] > array[max]) {
            max = r;
        }
        //если один из детей больше родителя, то
        if (i != max) {
            int temp = array[i]; //меняем большоего ребенка местами с родителем
            array[i] = array[max];
            array[max] = temp;

            heapify(array, max, n); //рекурсивно запускам функцию, чтобы обновить дерево при изменении родителя
        }
    }
}
