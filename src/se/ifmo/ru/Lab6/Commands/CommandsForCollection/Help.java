package se.ifmo.ru.Lab6.Commands.CommandsForCollection;

import se.ifmo.ru.Lab6.Commands.Command;
import se.ifmo.ru.Lab6.data.Ticket;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Help extends Command {
    private HashMap<String,Command> commands;
    public Help(String nameOfCommand) {
        super(nameOfCommand);
    }
    @Override
    public String ExecuteCommand(LinkedList<Ticket> ticket){
        String ans = "";
        for(Map.Entry<String, Command> entry:commands.entrySet() ){
            String key=entry.getKey();
            String value=entry.getValue().InfoOfCommand();
            ans+=key+": "+value+"\n";
        }
        return ans;
    }

    @Override
    public String  InfoOfCommand(){
        return "Выводит список команд";
    }

    public void setCommands(HashMap<String, Command> commands) {
        this.commands = commands;
    }
}
