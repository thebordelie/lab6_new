package se.ifmo.ru.Lab6.Commands.CommandsForCollection;

import se.ifmo.ru.Lab6.Commands.Command;
import se.ifmo.ru.Lab6.data.Ticket;

import java.util.LinkedList;

public class Clear extends Command {
    public Clear(String nameOfCommand) {
        super(nameOfCommand);
    }
    @Override
    public String ExecuteCommand(LinkedList<Ticket> tickets){
        tickets.clear();
        return "Коллекция успешно очищена";
    }
    @Override
    public String InfoOfCommand(){
        return "Очищает коллекцию";
    }
}
