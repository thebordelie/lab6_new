package se.ifmo.ru.Lab6.Client;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import se.ifmo.ru.Lab6.Commands.Command;
import se.ifmo.ru.Lab6.Commands.CommandRegister;
import se.ifmo.ru.Lab6.Commands.CommandsForCollection.Add;
import se.ifmo.ru.Lab6.data.Ticket;
import se.ifmo.ru.Lab6.data.Venue;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashMap;

public class SendCommand {
    private Socket socket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private BufferedReader keyboard;
    private ObjectOutputStream objectOutputStream;
    private CommandRegister commandRegister;
    private Ticket ticket;
    private Venue venue;
    private static final org.apache.log4j.Logger logger= Logger.getLogger(ConsoleManager.class.getName());
    public SendCommand(CommandRegister commandRegister) {
        try {
            socket=new Socket("localhost",3390);
            if(socket.isClosed()){}
            inputStream=new DataInputStream(socket.getInputStream());
            outputStream=new DataOutputStream(socket.getOutputStream());
            objectOutputStream=new ObjectOutputStream(socket.getOutputStream());
            keyboard=new BufferedReader(new InputStreamReader(System.in));
            this.commandRegister=commandRegister;
        }
        catch (UnknownHostException ex){
            logger.log(Level.ERROR,"the port is missing", ex);
            System.out.println("Неверное значение порта");
        }
        catch (IOException ex){
            System.out.println("Сервер закрыт, попробуйте позже");
            System.exit(1);
        }

    }
    public void Sender(Command command){
        try {
            logger.log(Level.INFO,"send command to server");
            objectOutputStream.writeObject(command);
            objectOutputStream.flush();
            logger.log(Level.INFO,"receiving a response to client");
            System.out.println(inputStream.readUTF());

        }
        catch (SocketException ex){
            System.out.println("Сервер отключен");
        }
        catch (IOException ex){
            logger.log(Level.ERROR,"Sending is not possible", ex);
            System.out.println("Невозможна отправка");

        }

    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}
