import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Lec3_collections
 */

public class Lec3_collections {
    
    public static void main(String[] args) {
        // Ex01_object();
        // Ex02_object();
        // Ex002_ArrayList();
        // Ex004_ArrayMethod();
        // Ex006_ListOf();
        Ex007_Iterator();
    }

    //Object - всему голова, любое значени любого типа может быть положено в него - упаковка
    //из object можно "распаковать" в любой тип (скастовать)
    //иерархия типов - любой тип меньше object'а
    public static void Ex01_object() {
        Object o = 1; GetType(o); //объект типа Integer 
        o = 1.2; GetType(o);  //объект типа double
        System.out.println(Sum(1, 2));
        System.out.println(Sum(1.0, 2));
        System.out.println(Sum(1, 2.0));
        System.out.println(Sum(1.2, 2.1));
        // System.out.println(Sum("abc", "cba")); //попробуй дописать сам

    }

    //не нужно писать кучу GetType для каждого типа данных
    private static void GetType(Object obj) {
        System.out.println(obj.getClass().getName());
    }

    //сумма объектов (чем больше умеет обрабатывать типо данных, тем больше типов, тем больше время работы метода)
    //чем меньше преобразований Object, тем лучше
    public static Object Sum(Object a, Object b) {
        if (a instanceof Double && b instanceof Double) { //если оба числа Double
            return (Object)((Double)a + (Double)b); //скастуем к Double и запаковываем в Object
        } else if (a instanceof Integer && b instanceof Integer) {
            return (Object)((Integer)a + (Integer)b);
        } else return 0;
    }

    //как увеличть размер массива по ходу кода
    public static void Ex02_object() {
        int[] a = new int[] {1, 9};
        for (int i : a) System.out.printf("%d ", i); //цикл for each loop 
        a = AddItem(a, 2);
        a = AddItem(a, 3);
        System.err.println();
        for (int i : a) System.out.printf("%d ", i);
    }

    //добавление элемента в массива по стандарту
    public static int[] AddItem(int[] array, int item) {
        //в стек кладутся те переменные размер которых уже известен, пример Integer 4 байта
        int length = array.length; 
        int[] temp = new int[length + 1]; //создаем в стеке указатель на ячейку памяти в куче размером (length + 1) * 4 байта
        System.arraycopy(array, 0, temp, 0, length); //в стеке определен temp указатель и перекладываются данные
        temp[length] = item;
        return temp;
        //такие операции занимают ресурс, проще немного увеличить исходный размер памяти (по одому элементу добавлять не выгодно)
        //как сделано в Java - если вылезаем на 1 элемент за пределы массива, то его размер увеличиваем в 2 раза
    }

    /*Collection=> Queue, List, Set (расширяют коллекции)
    есть коллекции устаревшие и не рекомендуемые к использованию в будущих проектах
    List - пронумерованный набор элементов
    Пользователь этого интерфейса имеет точный контроль над тем, где в списке вставляет каждый элемент
    Пользователь может обращаться к элементам по их целочисленному индеску и искать элементы в списке
    Примеры: ArrayList, LinkedList (Vector, Stack - устаревшие)
    Методы для одной коллекции буду и для любой другой из этой же иерархии
    */
    public static void Ex002_ArrayList() { 
        List<Integer> list = new ArrayList<Integer>(); //можно отправлять сырые данные, обобщение - правильно
        //List<Integer> list = new ArrayList(10); //создаем 10 элементов
        //List<Integer> list = new ArrayList(list3); //копируем list3 
        list.add(2809); //делаем неявное преобразование к Object (без <> указания обобщения)
        
        for (Object o : list) {
            System.out.println(o);
            //использование raw data (сырых данных) вызовет проблемы с выводом
        }
    }
    
    public static void Ex004_ArrayMethod() {
        //простой пример наполнения данными ArrayList'а
        int day = 29;
        int month = 9;
        int year = 1990;
        Integer[] data = {day, month,year};
        List<Integer> d = Arrays.asList(data);
        System.out.println(d);
        
        StringBuilder Day = new StringBuilder("28");
        StringBuilder Month = new StringBuilder("9");
        StringBuilder Year = new StringBuilder("1990");
        StringBuilder[] Data = new StringBuilder[]{Day, Month, Year};
        List<StringBuilder> D = Arrays.asList(Data);
        System.out.println(D);
        Data[1] = new StringBuilder("09"); //изменил данные в массиве
        System.out.println(D); //данные в коллекции тоже изменились потому что они образованы от значений массива которые
                               //хранятся в куче (heap)
    }

    public static void Ex006_ListOf() {
        Character value = null;
        List<Character> list1 = List.of('M', 'i', 'h', 'a', 'i', 'l'); 
        System.out.println(list1);
        list1.remove(1);
        List<Character> list2 = List.copyOf(list1);
    }

    public static void Ex007_Iterator() {
        List<Integer> list = List.of(1, 12, 12, 3,4, 4, 11);
        
        for (int item : list) {
            System.out.println(item);
        }

        Iterator<Integer> col = list.iterator(); //создаем итератор
        System.out.println();

        while (col.hasNext()) { //перебираем все элементы коллекции
            System.out.println(col.next());
            // col.next();
            // col.remove();
        }
    }

}