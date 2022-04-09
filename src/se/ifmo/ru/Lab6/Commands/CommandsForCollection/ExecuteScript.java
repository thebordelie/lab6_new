package se.ifmo.ru.Lab6.Commands.CommandsForCollection;

import se.ifmo.ru.Lab6.Commands.Command;

public class ExecuteScript extends Command {
    private String fileName;
    public ExecuteScript(String nameOfCommand) {
        super(nameOfCommand);
    }

    @Override
    public String InfoOfCommand(){
        return "Считать и выполнить скрипт из указанного файла";
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
