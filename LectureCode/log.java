/*
api для логгирования
*/

import java.io.IOException;
import java.util.logging.*;

public class log {
    public static void main(String[] args) throws IOException {
    
        Logger logger = Logger.getLogger(log.class.getName()); //создаем объект логгера
        // ConsoleHandler ch = new ConsoleHandler();
        FileHandler fh = new FileHandler("log.xml"); //создаем обработчик (пишем в файл)
        //logger.addHandler(ch);
        logger.addHandler(fh); //указываем в качетстве агрумента наш обработчик
        
        //SimpleFormatter sFormat = new SimpleFormatter(); //формат логгера
        XMLFormatter xml = new XMLFormatter();
        //fh.setFormatter(sFormat); //указываем формат в качестве аргумента
        fh.setFormatter(xml);
        
        //logger.setLevel(Level.INFO);
        logger.log(Level.WARNING, "Тестовое логирование 1"); //тип WARNING
        logger.info("Тестовое логирование 2"); //тип info

    }
}







