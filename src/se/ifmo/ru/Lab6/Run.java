package se.ifmo.ru.Lab6;

import org.apache.log4j.Level;
import se.ifmo.ru.Lab6.Client.ConsoleManager;

import org.apache.log4j.Logger;


/**
 * @author Керпик Артём Р3112
 */
public class Run {
    private static final Logger logger = Logger.getLogger(Run.class.getName());
    public static void main(String[] args) {
        logger.log(Level.INFO,"start client");
        System.out.println("Привет пользователь!");
        ConsoleManager consoleManager=new ConsoleManager();
        consoleManager.StartInteractiveMod();
        logger.log(Level.INFO,"stop");
    }
}
