/**
Алгоритм нахождения пути на 2D карте (пример трассировка печатных плат)
1. Инициилизировать карту
2. Запустить волну
3. Восстановить кратчайший путь
Стартовая позиция [7][2]
Финишная позиция [1][12]
 */
public class wave_algorithm {

    public static void main(String[] args) {
        int N = 10; //максимальный размер карты 256
        int M = 15; //на 256
        int[][] map = new int[N][M]; //карта (игровое поле, печатная плата)
        int start_x = 2; //стартовые позиции, ось ординат это строки, ось абцисс это столбы
        int start_y = 7;
        int stop_x = 12;
        int stop_y = 1;

        map = wall_random_generator(map, N, M);
        map[start_y][start_x] = -2; //задаем точки старта и финиша для наглядности
        map[stop_y][stop_x] = -3;
        //clear_terminal();
        printArr2D(map, N, M); //пустая карта
        map[start_y][start_x] = 0; //посмотрели визуально и хватит
        map[stop_y][stop_x] = 0;
        
        //делаем первый ход вне рекурсии
        first_step(map, start_x, start_y);
        int d = 1;
        update_map(map, d, M, N, stop_x, stop_y); //обновляет пути
        printArr2D(map, N, M);
    }

    //делаем обновление карты
    public static void update_map(int[][] map, int d, int M, int N, int stop_x, int stop_y) {
        for (int x = 1; x < M - 1; x++) { //от 1 до M - 1, чтобы не упираться в стены
            for (int y = 1; y < N - 1; y++) {
                if (map[y][x] == d) { //если нашли ячейку где элемент равен d
                    // System.out.printf("d = %d; y = %d; x = %d\n", d, y, x); //для отладки (просмотр по каким координатам есть совпадение с d)
                    if (map[y+1][x] == 0) map[y+1][x] = d + 1; //проверяем соседние элементы на 0 и пишем туда +1
                    if (map[y-1][x] == 0) map[y-1][x] = d + 1;
                    if (map[y][x+1] == 0) map[y][x+1] = d + 1;
                    if (map[y][x-1] == 0)  map[y][x-1] = d + 1;
                }
            }
        }
        if (map[stop_y][stop_x] > 0) return; //условие выходы из рекурсии
        d = (int) (d + 1); //увеличиваем длину маршрута
        update_map(map, d, M, N, stop_x, stop_y);
    }

    //функция первого шага
    public static void first_step(int[][] map, int start_x, int start_y) {
        if (map[start_y + 1][start_x] == 0) map[start_y + 1][start_x] = 1;  
        if (map[start_y - 1][start_x] == 0) map[start_y - 1][start_x] = 1;
        if (map[start_y][start_x + 1] == 0) map[start_y][start_x + 1] = 1;
        if (map[start_y][start_x - 1] == 0) map[start_y][start_x - 1] = 1;
    }

    //функция для печати двумерного массива
    public static void printArr2D(int[][] arr, int row, int col) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (arr[i][j] >= 0 && arr[i][j] < 10) System.out.print(arr[i][j] + "   ");
                if (arr[i][j] < 0 || arr[i][j] >= 10) System.out.print(arr[i][j] + "  ");
            }
            System.out.println();
        }
    }

    //очистка терминала
    public static void clear_terminal() {
        System.out.println("\033[2J\033[1;1H");
    }

    //рандомный генератор препятсвий
    public static int[][] wall_random_generator(int[][] map, int N, int M) {
        int x = 1;
        int y = 10;
        int wall_namber = 3;
        map[2][3] = -1;
        map[2][4] = -1;
        map[2][5] = -1;
        map[2][6] = -1;
        map[2][7] = -1;
        map[8][11] = -1;
        map[7][11] = -1;
        map[6][11] = -1;
        map[5][11] = -1;
        map[4][11] = -1;
        map[6][3] = -1;
        map[6][4] = -1;
        map[6][5] = -1;
        map[6][6] = -1;
        map[6][7] = -1;
        //рисуем стены
        for (int i = 0; i < M; i++) {
            map[0][i] = -1;
            map[N-1][i] = -1;
        }
        for (int i = 0; i < N; i++) {
            map[i][0] = -1;
            map[i][M-1] = -1;
        }
        return map;    
    }
}