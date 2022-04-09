package se.ifmo.ru.Lab6.Commands.CommandsForCollection;

import se.ifmo.ru.Lab6.Commands.Command;

public class Exit extends Command {
    public Exit(String name){
        super(name);
    }
    @Override
    public String InfoOfCommand(){
        return "Останавливает программу";
    }
}
