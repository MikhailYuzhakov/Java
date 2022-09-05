/*
 * На вход некоторому исполнителю подаётся два числа (a, b). У исполнителя есть две команды
- команда 2 (к2): увеличить в с раза, а умножается на c
- команда 1 (к1): увеличить на d ( +2 ), к a прибавляется d
написать программу, которая выдаёт набор команд, позволяющий число a превратить в число b или сообщить, что это невозможно
 */

public class task1 {

  static int[] solve(int start, int end, int com1, int com2) {
    int[] ways = new int[end + 1];
    ways[start] = 1;

    for (int index = start + com1; index <= end; index++) {

      if (index % com2 == 0) {
        ways[index] = ways[index - com1] + ways[index / com2];
      } else {
        ways[index] = ways[index - com1];
      }
    }
    return ways;
  }

  static String print(int[] items) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < items.length; i++) {
      sb.append(String.format("(%d)%d ", i, items[i]));
    }
    return sb.toString();
  }

  // находит одну последовательность команд
  public static void find_cmd_k2(int[] ways, int start, int end, int com1, int com2) {
      if ((start * com2 <= end) && (ways[start * com2] != 0)) { //если маршрут по команде 2 выкидывает за пределы массива, то дальше проверку не выполняем
        System.out.print("k2 ");
        find_cmd_k2(ways, start * com2, end, com1, com2); //вызываем ту же функцию с новым значением start
      }
      else if ((start + com1 <= end) && (ways[start + com1] != 0)) { //если маршрут команде 1 выкидывает за пределы массива, то дальше проверку не выполняем
          System.out.print("k1 ");
          find_cmd_k2(ways, start + com1, end , com1, com2); //вызываем ту же функцию с новым значением start
        } else return;
  }

  public static void main(String[] args) {
    int a = 2; // начальное число
    int b = 30; // конечное число
    int c = 3; // a*c
    int d = 4; // a+d

    int[] arr = solve(a, b, d, c); // решаем задачу по поиску количества маршрутов
    System.out.printf("Количество машрутов из a = %d в b = %d равно %d\n", a, b, arr[arr.length - 1]);

    System.out.println(print(arr)); //печать массива с маршрутами
    find_cmd_k2(arr, a, b, d, c); //поиск вариаций маршрутов
  }
}