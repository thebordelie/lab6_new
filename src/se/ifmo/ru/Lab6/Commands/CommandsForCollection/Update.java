package se.ifmo.ru.Lab6.Commands.CommandsForCollection;

import se.ifmo.ru.Lab6.Commands.Command;
import se.ifmo.ru.Lab6.data.Ticket;

import java.util.LinkedList;

public class Update extends Command {
    private long id;
    private Ticket ticket;
    public Update(String nameOfCommand) {
        super(nameOfCommand);
    }
    @Override
    public String ExecuteCommand(LinkedList<Ticket> tickets){
        for(Ticket ticket:tickets){
            if(ticket.getId()==id){
                tickets.set(tickets.indexOf(ticket),this.ticket);
                return "Элемент найден и обновлён";
            }
        }
        return "Элемент не удалось найти в коллекции";
    }
    @Override
    public String  InfoOfCommand(){
        return "Обновляет элемент по заданному id";
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}
