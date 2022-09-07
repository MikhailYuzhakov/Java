import java.io.File;
import java.io.FileWriter;

/**
 * Lecture2
 * API - application program interface
 */
public class Lecture2 {

    public static void main(String[] args) {
        // str_builder_api();
        // file_system();
        try_catch();
    }

    //string builder api позволяет намного быстрее собрать строку, но разрабоать/найти быстрее через стандартный string
    public static void str_builder_api() {
        String[] name = { "C", "е", "р", "г", "е", "й" }; //StringBuilder
        String sk = "СЕРГЕЙ КА.";
        System.out.println(sk.toLowerCase()); // сергей ка.
        System.out.println(String.join("", name)); // Cергей
        System.out.println(String.join("", "C", "е", "р", "г", "е", "й"));
        // C,е,р,г,е,й
        System.out.println(String.join(",", "C", "е", "р", "г", "е", "й"));
    }

    //api для работы с файловой системой
    public static void file_system() {
        String pathProject = System.getProperty("user.dir");
        String pathFile = pathProject.concat("/file.txt");
        File f3 = new File(pathFile);
        System.out.println(f3.getAbsolutePath());

        // System.out.println(pathFile);
        File f1 = new File("file.txt");
        System.out.println(f1.getAbsolutePath());
    }

    //api для обработки ошибок (если можно от них избавиться, лучше избавиться)
    public static void try_catch() {
        String line = "empty";
        try { //пробуем выполнить кусок кода
            String pathProject = System.getProperty("user.dir"); //прочитали директорию пользователя
            String pathFile = pathProject.concat("/file.txt"); //конкатенация к директории названия
            File file = new File(pathFile); //создаем объект для работы с файловой системой

            if (file.createNewFile()) {
                System.out.println("file.created");
            }
            else {
                System.out.println("file.existed");
                FileWriter fileWriter = new FileWriter(file, true);
                fileWriter.write("new line");

                //#region lineSeparator
                // A string containing "\r\n" for non-Unix 
                // platforms, or a string containing 
                // "\n" for Unix platforms.
                fileWriter.append(System.lineSeparator());
                //#endregion

                fileWriter.write("new line");
                fileWriter.append("new line");
                fileWriter.flush();
                fileWriter.close();
                // BufferedReader bufReader = new BufferedReader(new FileReader(file));
                // line = bufReader.readLine();
                // bufReader.close();
            }
        } catch (Exception e) { //если не получилось выдаем ошибку
            e.printStackTrace();
        } finally { //выполняется в любом случае
            System.out.println(line);
        }
    }
}