/*
Реализована работа с двоичными файлами, считывание и запись.
Релизовано использование структуры try catch finally для обработки ошибок.
Реализовано логгирование
*/

import java.io.*; //добавляет все функции из библиотеки
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.io.IOException;
import java.util.logging.*;

public class bin_with_logs {
    static ByteOrder bOrder = ByteOrder.LITTLE_ENDIAN; //создаем объект ByteOreder младшим байтом вперед
    static Map<String, String> data = new HashMap<>();
    static Charset charset = StandardCharsets.UTF_8;

    //загрузить файл
    static void loadFile(String path) throws IOException {
        try (InputStream stream = 
        new BufferedInputStream(new FileInputStream(path))) {
            int n = readInt(stream);
            int b1;
            int b2;
            byte binWord[];
            byte binText[];
            for (int i = 0; i < n; i++) {
                binWord = new byte[readInt(stream)];
                binText = new byte[readInt(stream)];
                b1 = stream.read(binWord);
                b2 = stream.read(binText);
                if (b1 != binWord.length || b2 != binText.length)
                    throw new IOException("Error read file");
                data.put(new String(binWord, charset), 
                            new String(binText, charset));
            }
        }
    }
    //сохранить файл
    static boolean saveFile(String path) throws IOException {
        if (data.size() == 0)
            throw new IOException("Nothing to write");
 
        try (OutputStream stream = new BufferedOutputStream(new FileOutputStream(path, false))) {
            writeInt(stream, data.size());
            byte binWord[];
            byte binText[];
            for (Entry<String, String> entry : data.entrySet()) {
                binWord = entry.getKey().getBytes(charset);
                writeInt(stream, binWord.length);
                binText = entry.getValue().getBytes(charset);
                writeInt(stream, binText.length);
                stream.write(binWord);
                stream.write(binText);
            }
        }
        return true;
    }
    //удалить слово
    static void delete(String word) {
        data.remove(word);
    }
    //добавить слово
    static void add(String word, String text) throws Exception {
        if (data.get(word) != null)
            throw new Exception("Word already exist");
        data.put(word, text);
    }
    //найти слово
    static String find(String word) {
        String out = data.get(word);
        if (out == null)
            return "не найдено";
        else
            return out;
    }
    //считать Int
    static int readInt(InputStream in) throws IOException {
        byte out[] = new byte[4];
        int i = in.read(out);
        if (i != 4)
            throw new IOException("Error read file");
        return ByteBuffer.wrap(out).order(bOrder).getInt();
    }
    //записать Int
    static void writeInt(OutputStream out, int num) throws IOException {
        ByteBuffer dbuf = ByteBuffer.allocate(4);
        dbuf.order(bOrder).putInt(num);
        out.write(dbuf.array());
    }

    public static void main(String[] args) throws SecurityException, IOException  {
        Logger logger = Logger.getLogger(log.class.getName()); //создаем объект логгера
        FileHandler fh = new FileHandler("log.xml"); //создаем обработчик (пишем в файл)
        logger.addHandler(fh); //указываем в качетстве агрумента наш обработчик
        XMLFormatter xml = new XMLFormatter();
        fh.setFormatter(xml);
        
        String file = "test.bin";
        boolean isFileWrite = false;

        //запись файла
        try {
            add("key1", "значение 1");
            add("key2", "значение 2");
            add("key4", "значение 3");
            isFileWrite = saveFile(file);
            if (isFileWrite) logger.info("Writing to " + file + " is success"); //тип info
            else logger.log(Level.WARNING, "Writing to " + file + " is failed"); //тип WARNING 
        } catch (Exception e) {
            e.printStackTrace();
            logger.log(Level.WARNING, "Writing to " + file + " is failed"); //тип WARNING
        }
 
        //чтение файла
        try {
            loadFile(file);
            String key1 = "key1";
            String key2 = "key2";
            String key3 = "key3";
            System.out.printf("%s: %s\n", key1, find(key1));
            System.out.printf("%s: %s\n", key2, find(key2));
            System.out.printf("%s: %s\n", key3, find(key3));
            logger.info("Reading from " + file + " is success"); //тип info
        } catch (IOException e) {
            e.printStackTrace();
            logger.log(Level.WARNING, "Reading from " + file + " is failed"); //тип WARNING
        }
    }
}
