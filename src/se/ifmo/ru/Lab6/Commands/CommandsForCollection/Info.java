package se.ifmo.ru.Lab6.Commands.CommandsForCollection;

import se.ifmo.ru.Lab6.Commands.Command;
import se.ifmo.ru.Lab6.data.Ticket;

import java.util.LinkedList;

public class Info extends Command {
    public Info(String nameOfCommand) {
        super(nameOfCommand);
    }
    @Override
    public String ExecuteCommand(LinkedList<Ticket> tickets) {
        return "Коллекция содержит элементы класса Ticket\n В коллекции сейчас находится " +tickets.size()+" элементов";
    }
    @Override
    public String  InfoOfCommand(){
        return "Выводит информацию о коллекции";
    }

}
