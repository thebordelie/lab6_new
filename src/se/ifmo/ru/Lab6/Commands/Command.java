package se.ifmo.ru.Lab6.Commands;

import se.ifmo.ru.Lab6.data.Ticket;

import java.io.Serializable;
import java.util.LinkedList;

public class Command implements Serializable {
    private String arg;
    private final String nameOfCommand;
    public Command(String nameOfCommand){
        this.nameOfCommand=nameOfCommand;
    }
    public String InfoOfCommand(){return "";}
    public String ExecuteCommand(LinkedList<Ticket> tickets){return "";}
    public String getNameOfCommand(){
        return nameOfCommand;
    }
    @Override
    public String toString(){
        return "Команда "+nameOfCommand+", предназначена для работы с коллекцией";
    }

    public String getArg() {
        return arg;
    }

    public void setArg(String arg) {
        this.arg = arg;
    }
}
