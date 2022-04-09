package se.ifmo.ru.Lab6.Server;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import se.ifmo.ru.Lab6.Client.ConsoleManager;
import se.ifmo.ru.Lab6.Commands.Command;
import se.ifmo.ru.Lab6.data.Ticket;
import se.ifmo.ru.Lab6.data.TicketList;
import se.ifmo.ru.Lab6.utility.DOM;
import se.ifmo.ru.Lab6.utility.JaxbWorker;
import se.ifmo.ru.Lab6.utility.TicketAsker;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class ServerCollectionManager {
    /**Свойство - Сохраняет коллекцию*/
    private JaxbWorker jaxbWorker;
    /**Свойство - дата*/
    private LocalDate localDate;
    /**Свойство - Связный список, хранящий билеты*/
    private LinkedList<Ticket> tickets;
    /**Свойство - объект, хранящий в себе список билетов */
    private TicketList ticketList;
    private String env;
    private static final org.apache.log4j.Logger logger= Logger.getLogger(ServerCollectionManager.class.getName());
    public ServerCollectionManager(String env){
        this.env=env;
        tickets=new LinkedList<>();
        localDate=LocalDate.now();
        DOM dom =new DOM(tickets);

        try{
            dom.readingFile(env);
        }
        catch (ParserConfigurationException e) {
            System.out.println("Невозможно прочитать файл");
        } catch (IOException e) {
            System.out.println("Файл отсутствует");
        } catch (SAXException e) {
            System.out.println("Ошибка!");
        }
        tickets.sort(Comparator.comparing(Ticket::getId));
        ticketList=new TicketList(tickets);
    }
    public String  ExecuteCommand(Command command){
        logger.log(Level.INFO,"execute command");
        return command.ExecuteCommand(tickets);
    }

}
