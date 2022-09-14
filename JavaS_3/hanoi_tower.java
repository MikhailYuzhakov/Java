/*
Реализовать алгоритм игры Ханойская башня
*/

public class hanoi_tower {
    public static void main(String[] args) {
        byte N = 5; //количество дисков
        byte[][] play_field = new byte[N][3]; //величина элемента массива это размер диска, три колонки это три оси
        for (int i = 0; i < play_field.length; i++) { //"надеваем диски на одну из осей"
            play_field[i][0] = (byte)(i+1); //блины кладем на 1-ую ось
        }
        print_play_field(play_field);
        find_solve(play_field, 1, play_field.length - 1, 0, 2); //блины перекладываем на 2-ую ось (0 индексирование)
        print_play_field(play_field);
    }

    //печать игрового поля
    public static void print_play_field(byte[][] map) {
        for (int i = 0; i < map.length; i++)
        {
            for (int j = 0; j < map[0].length; j++) {
                System.out.printf("%d  |  ", map[i][j]); 
            }
            System.out.println();
        }
        System.out.println("-----------------");
    }

    //поиск и печать решения
    public static void find_solve(byte[][] map, int target_axis, int d, int cur_axis, int empty_axis) {
        int sum = 0;
        d = d - 1;
        for (int i = d; i >= 0; i--) {
            map[i + 1][empty_axis] = map[i][cur_axis];
            map[i][cur_axis] = 0;
        }
        map[d][target_axis] = map[d][cur_axis];
        map[d][cur_axis] = 0;

        //ищем пустую ось
        for (int i = 0; i < map.length; i++) sum = sum + map[i][0];
        if (sum == 0) empty_axis = 0;
        sum = 0;

        for (int i = 0; i < map.length; i++) sum = sum + map[i][1];
        if (sum == 0) empty_axis = 1;
        sum = 0;

        for (int i = 0; i < map.length; i++) sum = sum + map[i][2];
        if (sum == 0) empty_axis = 2;
        sum = 0;

        //определяем значение оставшейся оси
        if ((target_axis == 0 && empty_axis == 1) || (target_axis == 1 && empty_axis == 0)) cur_axis = 2;
        if ((target_axis == 0 && empty_axis == 2) || (target_axis == 2 && empty_axis == 0)) cur_axis = 1;
        if ((target_axis == 1 && empty_axis == 2) || (target_axis == 2 && empty_axis == 1)) cur_axis = 3;

        System.out.printf("d = %d, cur_axis = %d, empty_axis = %d\n", d, cur_axis, empty_axis);

        // if (map[0][target_axis] == 0) find_solve(map, target_axis, d, cur_axis, empty_axis);
        // else return;
        return;
    }
}
