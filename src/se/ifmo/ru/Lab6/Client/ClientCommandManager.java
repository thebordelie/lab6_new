package se.ifmo.ru.Lab6.Client;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import se.ifmo.ru.Lab6.Commands.Command;
import se.ifmo.ru.Lab6.Commands.CommandRegister;
import se.ifmo.ru.Lab6.Commands.CommandsForCollection.*;
import se.ifmo.ru.Lab6.data.Ticket;
import se.ifmo.ru.Lab6.data.Venue;
import se.ifmo.ru.Lab6.exception.ElementMustNotBeEmptyException;
import se.ifmo.ru.Lab6.exception.RecursiveCommandException;
import se.ifmo.ru.Lab6.utility.TicketAsker;

import java.io.*;
import java.time.LocalDate;
import java.util.Scanner;

public class ClientCommandManager {
    private TicketAsker ticketAsker;
    private CommandRegister commandRegister;
    private SendCommand sendCommand;
    private Ticket ticket;
    private static final Logger logger=Logger.getLogger(ConsoleManager.class.getName());
    public ClientCommandManager(CommandRegister commandRegister){
        this.commandRegister=commandRegister;
        sendCommand=new SendCommand(commandRegister);
        ticketAsker=new TicketAsker(new Scanner(System.in));;
    }
    public void SendCommand(String[] command){
        long id;
        String InId;
        Command comm;
        logger.log(Level.INFO,"processing commands");
        switch (command[0]){
            case "help":
                Help help=new Help("help");
                help.setCommands(commandRegister.getCommands());
                sendCommand.Sender(help);
                break;
            case "add":
                id=(long)(Math.random()* Integer.MAX_VALUE);
                System.out.println("Введите параметры добавляемого билета ");
                ticket=new Ticket(id,ticketAsker.askName(),ticketAsker.askCoordinates(), LocalDate.now(),ticketAsker.askPrice(), ticketAsker.askRefundable(), ticketAsker.askTicketType(),new Venue(id, ticketAsker.askName(), ticketAsker.askCapacity(), ticketAsker.askAddress()));
                Add add=new Add("add");
                add.setTicket(ticket);
                sendCommand.Sender(add);
                break;
            case "remove_any_by_venue":
                Venue venue=new Venue((long)(Math.random()*10),ticketAsker.askName(), ticketAsker.askCapacity(), ticketAsker.askAddress());
                RemoveAnyByVenue removeAnyByVenue=new RemoveAnyByVenue("remove_any_by_venue");
                removeAnyByVenue.setVenue(venue);
                sendCommand.Sender(removeAnyByVenue);
                break;
            case "add_if_min":
                id=(long)(Math.random()* Integer.MAX_VALUE);
                System.out.println("Введите параметры, вводимого элемента");
                ticket=(new Ticket(id,ticketAsker.askName(),ticketAsker.askCoordinates(), LocalDate.now(),ticketAsker.askPrice(), ticketAsker.askRefundable(), ticketAsker.askTicketType(),new Venue(id, ticketAsker.askName(), ticketAsker.askCapacity(), ticketAsker.askAddress())));
                AddIfMin addIfMin=new AddIfMin("add_if_min");
                addIfMin.setTicket(ticket);
                sendCommand.Sender(addIfMin);
                break;
            case "update":
                try {
                    id=Long.parseLong(command[1]);
                    System.out.println("Введите обновленный элемент");
                    ticket=(new Ticket(id,ticketAsker.askName(),ticketAsker.askCoordinates(), LocalDate.now(),ticketAsker.askPrice(), ticketAsker.askRefundable(), ticketAsker.askTicketType(),new Venue(id, ticketAsker.askName(), ticketAsker.askCapacity(), ticketAsker.askAddress())));
                    Update update=new Update("update");
                    update.setTicket(ticket);
                    update.setId(id);
                    sendCommand.Sender(update);
                }
                catch (NumberFormatException ex){
                    System.out.println("Введён некорректный id");
                }
                catch (ArrayIndexOutOfBoundsException ex){
                    System.out.println("Не введён id");
                }
                break;
            case "execute_script":
                String fileCommand;
                String[] fileFinalCommand;
                try {
                    BufferedReader inputStreamReader = new BufferedReader(new InputStreamReader(new FileInputStream(command[1])));
                    fileCommand = inputStreamReader.readLine();
                    while (fileCommand!=null){
                        fileFinalCommand=fileCommand.trim().split(" ",2);
                        System.out.println("Началась обработка команды "+fileFinalCommand[0]);
                        if(fileFinalCommand[0].equals("exit")){
                            break;
                        }
                        if (fileFinalCommand[0].equals("execute_script")){
                            System.out.println("Невозможно выполнить такую команду");
                            break;
                        }
                        else SendCommand(fileFinalCommand);

                        fileCommand=inputStreamReader.readLine();
                    }
                }
                catch (FileNotFoundException ex){
                    System.out.println("Файл не найден");
                }
                catch (IOException e){
                    System.out.println("Считывание невозможно");
                }
                catch (ArrayIndexOutOfBoundsException ex){
                    System.out.println("Не введено название файла");
                }
                break;
            case "exit":
                sendCommand.Sender(new Exit("exit"));
                logger.log(Level.INFO,"stop a program");
                System.exit(1);
                break;
            case "remove_by_id":
                try {
                    id=Long.parseLong(command[1]);
                    Remove remove=new Remove("remove_by_id");
                    remove.setId(id);
                    sendCommand.Sender(remove);
                }
                catch (ArrayIndexOutOfBoundsException ex){
                    System.out.println("Вы не ввели значение id");
                }
                catch (NumberFormatException ex){
                    System.out.println("Вы ввели некорректное id");
                }
                break;
            case "save":
                System.out.println("Эта команда недоступна вам");
                break;
            case "count_less_than_refundable":
                try {
                    CountLessThanRefundable countLessThanRefundable=new CountLessThanRefundable("count_less_than_refundable");
                    countLessThanRefundable.setRefundable(Boolean.parseBoolean(command[1]));
                    sendCommand.Sender(countLessThanRefundable);
                }
                catch (ArrayIndexOutOfBoundsException ex){
                    System.out.println("Вы не ввели значение refundable");
                }
                catch (NumberFormatException ex){
                    System.out.println("Вы ввели некорректное refundable");
                }
                break;
            default:
                if(commandRegister.getCommands().containsKey(command[0])){
                    sendCommand.Sender(commandRegister.getCommands().get(command[0]));
                }

                else {logger.log(Level.WARN,"there is no command");System.out.println("Команда не найдена, напишите 'help' для получения списка возможных команд");}
        }



    }
}
