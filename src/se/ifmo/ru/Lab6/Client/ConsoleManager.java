package se.ifmo.ru.Lab6.Client;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import se.ifmo.ru.Lab6.Commands.CommandRegister;
import se.ifmo.ru.Lab6.Commands.CommandsForCollection.*;
import se.ifmo.ru.Lab6.exception.RecursiveCommandException;

import java.io.*;

public class ConsoleManager {
    private BufferedReader keyboard;
    private SendCommand sendCommand;
    private CommandRegister commandRegister;
    private ClientCommandManager commandManager;
    private static final Logger logger=Logger.getLogger(ConsoleManager.class.getName());
    public ConsoleManager()  {
        keyboard=new BufferedReader(new InputStreamReader(System.in));
        commandRegister=new CommandRegister();
        commandRegister.RegisterNewCommand("add",new Add("add"));
        commandRegister.RegisterNewCommand("add_if_min",new AddIfMin("add_if_min"));
        commandRegister.RegisterNewCommand("clear",new Clear("clear"));
        commandRegister.RegisterNewCommand("count_less_than_refundable", new CountLessThanRefundable("count_less_than_refundable"));
        commandRegister.RegisterNewCommand("execute_script",new ExecuteScript("execute_script"));
        commandRegister.RegisterNewCommand("group_counting_by_type",new GroupCountingType("group_counting_by_type"));
        commandRegister.RegisterNewCommand("head",new Head("head"));
        commandRegister.RegisterNewCommand("help",new Help("help"));
        commandRegister.RegisterNewCommand("info",new Info("info"));
        commandRegister.RegisterNewCommand("remove_by_id",new Remove("remove_by_id"));
        commandRegister.RegisterNewCommand("remove_any_by_venue",new RemoveAnyByVenue("remove_any_by_venue"));
        commandRegister.RegisterNewCommand("remove_head",new RemoveHead("remove_head"));
        commandRegister.RegisterNewCommand("show",new Show("show"));
        commandRegister.RegisterNewCommand("update",new Update("update"));
        commandRegister.RegisterNewCommand("exit",new Exit("exit"));
        commandManager=new ClientCommandManager(commandRegister);

    }
    public void StartInteractiveMod(){
        String usersCommand;
        String[] finalComamnd;
        try {
            while (true){
                logger.log(Level.INFO,"reading user commands");
                System.out.println("Введите команду:");
                usersCommand= keyboard.readLine();
                finalComamnd=usersCommand.trim().split(" ",2);
                commandManager.SendCommand(finalComamnd);
            }
        }

        catch (IOException e){
            System.out.println("Ошибка подключения");
        }
        catch (NullPointerException ex){
            System.out.println("Сервер умер");
            logger.log(Level.ERROR,"the server is suicide", ex);
        }


    }
}
