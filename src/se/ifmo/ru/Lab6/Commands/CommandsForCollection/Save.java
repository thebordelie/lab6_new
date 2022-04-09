package se.ifmo.ru.Lab6.Commands.CommandsForCollection;

import se.ifmo.ru.Lab6.Commands.Command;
import se.ifmo.ru.Lab6.data.Ticket;

import java.util.LinkedList;

import se.ifmo.ru.Lab6.data.TicketList;
import se.ifmo.ru.Lab6.utility.JaxbWorker;
public class Save extends Command {
    public Save(String name){
        super(name);
    }
    @Override
    public String InfoOfCommand(){
        return getNameOfCommand()+" сохраняет коллекцию в файл";
    }
    @Override
    public String ExecuteCommand(LinkedList<Ticket> tickets){
        TicketList ticketList=new TicketList(tickets);
        String file_name="input.xml";
        JaxbWorker jaxbWorker=new JaxbWorker(ticketList,file_name);
        jaxbWorker.convertObjectToXml();
        System.out.println("Файл успешно сохранён");
        return "done";
    }
}
