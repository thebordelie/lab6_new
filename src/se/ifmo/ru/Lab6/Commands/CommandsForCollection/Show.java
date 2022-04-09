package se.ifmo.ru.Lab6.Commands.CommandsForCollection;

import se.ifmo.ru.Lab6.Commands.Command;
import se.ifmo.ru.Lab6.data.Ticket;

import java.util.LinkedList;

public class Show extends Command {
    public Show(String nameOfCommand) {
        super(nameOfCommand);
    }
    @Override
    public String ExecuteCommand(LinkedList<Ticket> tickets){
        String ans;
        if (tickets.size()==0){
            return "Коллекция пуста";
        }
        else{
            ans="В данный момент хранятся следующие элементы:\n";
            for(Ticket ticket: tickets){
                ans+=ticket+"\n";
            }
            return ans;
        }
    }

    @Override
    public String InfoOfCommand(){
        return "Выводит все элементы коллекции в строковом представлении";
    }
}
