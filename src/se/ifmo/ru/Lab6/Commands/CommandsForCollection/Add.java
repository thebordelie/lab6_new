package se.ifmo.ru.Lab6.Commands.CommandsForCollection;

import se.ifmo.ru.Lab6.Commands.Command;
import se.ifmo.ru.Lab6.data.*;

import java.util.LinkedList;

public class Add extends Command {
    private Ticket ticket;
    public Add(String nameOfCommand) {
        super(nameOfCommand);
    }
    @Override
    public String ExecuteCommand(LinkedList<Ticket> tickets){
        tickets.add(ticket);
        return ("Элемент успешно добавлен");
    }
    @Override
    public String InfoOfCommand(){
        return "Добавляет в коллекцию новый билет";
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}
