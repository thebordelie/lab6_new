package se.ifmo.ru.Lab6.Commands.CommandsForCollection;

import se.ifmo.ru.Lab6.Commands.Command;
import se.ifmo.ru.Lab6.data.Ticket;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class RemoveHead extends Command {
    public RemoveHead(String nameOfCommand) {
        super(nameOfCommand);
    }
    @Override
    public String ExecuteCommand(LinkedList<Ticket> tickets){
        try{
            return "Удаленный элемент: "+tickets.remove().getName();

        }
        catch (NoSuchElementException e){
            return "В коллекции отсутствуют элементы, удаление невозможно";
        }
    }

    @Override
    public String InfoOfCommand(){
        return "Удаляет первый элемент коллекции";
    }
}
