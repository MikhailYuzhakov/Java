/**
Алгоритм нахождения пути на 2D карте (пример трассировка печатных плат)
1. Инициилизировать карту
2. Запустить волну
3. Восстановить кратчайший путь
Стартовая позиция [7][2]
Финишная позиция [1][12]
Алгоритм реализован без явной очереди обхода соседних ячеек, для такого графа (где открыты все пути) - работает :)
 */
public class wave_algorithm {

    public static void main(String[] args) {
        int N = 10; //максимальный размер карты по высоте
        int M = 15; //по ширине
        int[][] map = new int[N][M]; //карта (игровое поле, печатная плата)
        int start_x = 2; //стартовые позиции, ось ординат это строки, ось абцисс это столбы
        int start_y = 7;
        int stop_x = 12; //координаты финиша
        int stop_y = 1;

        System.out.print("Пустое поле\n" + arr2DtoStr(map));
        wall_random_generator(map); //генерируем препятствия НЕ рандомно (до рандома руки не дошли)
        map[start_y][start_x] = -2; //задаем точки старта и финиша для наглядности
        map[stop_y][stop_x] = -3;
        System.out.print("Сгенерированное поле (со стенами и препятствиями)\n" + arr2DtoStr(map));

        map[stop_y][stop_x] = 0; //обнуляем финишную точку для корректоного заполнения карты
        first_step(map, start_x, start_y); //делаем первый ход вне рекурсии
        int d = 1; //сделали 1ый ход
        update_map(map, d, stop_x, stop_y); //обновляет пути (веса путей от вершин графа)
        System.out.print("Карта с весами (длинами путей) маршрутов\n" + arr2DtoStr(map)); //печатаем карту с весами маршрутов

        map[start_y][start_x] = 0; //для корректоного нахождения обратного пути
        find_path(map, stop_x, stop_y, start_x, start_y); //восстанавливаем один из кратчайших путей
        paint_path(map); //приводим к визуально приятному взгляду человека эту карту (+- приятному)
        System.out.println("Карта с кратчайшим путем\n" + arr2DtoStr(map)); //выводим обратный путь в консоль
    }

    //восстановить один их самых коротких путей
    public static void find_path(int[][] map, int x, int y, int start_x, int start_y) {
        if (x == start_x && y == start_y) { //пока не дошли на стартовых координат
            map[y][x] = -4;   
            return;
        }
        else {
            //ищем ту точку, где значение меньше на единицу (очередность та же, что и при построении карты весов)
            // System.out.printf("d = %d, x = %d, y = %d\n", map[y][x], x, y); // для отладки
            if (map[y + 1][x] == map[y][x] - 1) {
                map[y][x] = -4;
                y++;
                find_path(map, x, y, start_x, start_y);
            }
            if (map[y - 1][x] == map[y][x] - 1) {
                map[y][x] = -4;
                y--;
                find_path(map, x, y, start_x, start_y);
            }
            if (map[y][x + 1] == map[y][x] - 1) {
                map[y][x] = -4;
                x++;
                find_path(map, x, y, start_x, start_y);
            }
            if (map[y][x - 1] == map[y][x] - 1) {
                map[y][x] = -4;
                x--;
                find_path(map, x, y, start_x, start_y);
            }
        }

    }

    //визуально помечаем кратчайший пути (1 - препятствия, 7 - кратчайший путь, 0 - нет препятствий)
    public static void paint_path(int[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] > 0) map[i][j] = 0;
                if (map[i][j] == -1) map[i][j] = 1;
                if (map[i][j] == -4) map[i][j] = 7;
            }
        }
    }

    // *делаем обновление карты
    public static void update_map(int[][] map, int d, int stop_x, int stop_y) {
        for (int x = 1; x < map[0].length - 1; x++) { //от 1 до M - 1, чтобы не упираться в стены
            for (int y = 1; y < map.length - 1; y++) {
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
        d = d + 1; //увеличиваем длину маршрута
        update_map(map, d, stop_x, stop_y);
    }

    //функция первого шага
    public static void first_step(int[][] map, int start_x, int start_y) {
        if (map[start_y + 1][start_x] == 0) map[start_y + 1][start_x] = 1; //что-то вроде очереди обхода (вниз) 
        if (map[start_y - 1][start_x] == 0) map[start_y - 1][start_x] = 1; //вверх
        if (map[start_y][start_x + 1] == 0) map[start_y][start_x + 1] = 1; //вправо
        if (map[start_y][start_x - 1] == 0) map[start_y][start_x - 1] = 1; //влево
    }

    // *функция для печати двумерного массива
    public static String arr2DtoStr(int[][] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] >= 0 && arr[i][j] < 10) sb.append(arr[i][j] + "   ");
                if (arr[i][j] < 0 || arr[i][j] >= 10) sb.append(arr[i][j] + "  ");
            }
            sb.append("\n");
        }
        for (int i = 0; i < arr[0].length; i++) sb.append("----"); //печатаем разделитель
        sb.append("\n");
        return sb.toString();
    }

    // *НЕ рандомный (когда-нибудь потом) генератор препятсвий - возвращает сгенерированную карту
    public static void wall_random_generator(int[][] map) {
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
        for (int i = 0; i < map[0].length; i++) {
            map[0][i] = -1;
            map[map.length - 1][i] = -1;
        }
        for (int i = 0; i < map.length; i++) {
            map[i][0] = -1;
            map[i][map[0].length - 1] = -1;
        }  
    }
}