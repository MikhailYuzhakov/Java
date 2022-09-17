/**
 * hanoi_tower
 */
public class hanoi_tower {

    public static void main(String[] args) {
        hanoi(4, 1, 3); //всего 5 колец, переносим с 1ой оси на 3ю ось
    }

    public static void hanoi(int n, int i, int k) { //n - количество дисков, i - откуда переносим, k - куда переносим
        if (n == 1)
            System.out.printf("1: %d -> %d\n", i, k); //граничный случай, переносим первый диск последним шагом
        else {
            int temp = 6 - i - k; //вычисляем номер вспомогательной оси
            hanoi(n - 1, i, temp); //перемещаем из i оси на временную ось все оставшиеся n-1 дисков
            System.out.printf("%d: %d -> %d\n", n, i, k); //показываем какой диск и куда переносим
            hanoi(n - 1, temp, k); //переносим диски из временной оси на целевую
        }
    }
}