package se.ifmo.ru.Lab6.Commands.CommandsForCollection;

import se.ifmo.ru.Lab6.Commands.Command;
import se.ifmo.ru.Lab6.data.Ticket;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class Remove extends Command {
    private long id;
    public Remove(String nameOfCommand) {
        super(nameOfCommand);
    }
    @Override
    public String ExecuteCommand(LinkedList<Ticket> tickets){
        try {
            for (Ticket ticket:tickets){
                if(ticket.getId()==id){
                    tickets.remove(ticket);
                    return "Элемент удалён";
                }
                if (ticket.equals(tickets.getLast())){
                    return "Элемент не найден";
                }
            }
        }
        catch (NoSuchElementException ex){
            return "Коллекция пуста, удаление невозможно";
        }
        return "";
    }
    @Override
    public String  InfoOfCommand(){
        return "Удаляет элемент коллекции по заданному id";
    }

    public void setId(long id) {
        this.id = id;
    }
}

