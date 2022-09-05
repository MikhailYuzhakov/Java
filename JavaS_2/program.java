import java.util.Random;

/**
 * Найти наибольшую общую подпоследовательность
 */
public class program {
    static Random rnd = new Random();
    public static void main(String[] args) {
        int[] a = new int[] {7, 8, 5, 4, 4, 8, 5}; // первая последовательность
        int[] b = new int[] {2, 3, 4, 6, 5, 5, 6}; // вторая последовательность, наиб. общая. подпосл. {1, 2, 1, 3, 8}
        int[] c = new int[100];

        int k = 0;
        int l = 0;
        int p = 0;

        // a = gen_array(7); // сгенерировать случайную последовательность
        // b = gen_array(7);

        for (int i = l; i < a.length; i++) {
            for (int j = p; j < b.length; j++) {
                if (a[i] == b[j] & i <= j) {
                    c[k] = a[i];
                    k++;
                    p = j + 1;
                    break;
                }
            }
            l = i;
        }

        System.out.print("Последовательность 1: ");
        printArr(a, a.length);
        System.out.print("Последовательность 2: ");
        printArr(b, b.length);
        System.out.print("Наибольшая общая подпоследовательность: ");
        printArr(c, k);
    }

    public static void printArr(int[] arr, int length) {
        for (int i = 0; i < length; i++)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    public static int[] gen_array(int length) {
        int [] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = rnd.nextInt(10);
        }
        return arr;
    }
}