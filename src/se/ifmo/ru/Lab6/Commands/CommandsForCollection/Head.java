package se.ifmo.ru.Lab6.Commands.CommandsForCollection;

import se.ifmo.ru.Lab6.Commands.Command;
import se.ifmo.ru.Lab6.data.Ticket;

import java.util.LinkedList;

public class Head extends Command {
    public Head(String nameOfCommand) {
        super(nameOfCommand);
    }
    @Override
    public String ExecuteCommand(LinkedList<Ticket> tickets){
        if(tickets.size()==0){
            return "Коллекция пуста, невозможно вывести первый элемент";
        }
        return "Первый элемент коллекции \n"+tickets.get(0).toString();
    }
    @Override
    public String InfoOfCommand(){
        return "Выводит первый элемент коллекции";
    }
}
