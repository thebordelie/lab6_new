package se.ifmo.ru.Lab6.Server;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import se.ifmo.ru.Lab6.Client.ConsoleManager;
import se.ifmo.ru.Lab6.Commands.Command;
import se.ifmo.ru.Lab6.Commands.CommandsForCollection.Add;
import se.ifmo.ru.Lab6.Commands.CommandsForCollection.AddIfMin;
import se.ifmo.ru.Lab6.Commands.CommandsForCollection.Save;
import se.ifmo.ru.Lab6.data.Ticket;

import java.io.*;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.Scanner;

public class Server {
    private final static int port=3345;
    private static final org.apache.log4j.Logger logger= Logger.getLogger(Server.class.getName());
    public static void main(String[] args) {
        try {
            logger.log(Level.INFO,"start a server");
            ServerSocket serverSocket = new ServerSocket(3390);
            Socket client = serverSocket.accept();
            DataInputStream inputStream=new DataInputStream(client.getInputStream());
            DataOutputStream outputStream=new DataOutputStream(client.getOutputStream());
            ObjectInputStream objectInputStream=new ObjectInputStream(client.getInputStream());
            ServerCollectionManager serverCollectionManager=new ServerCollectionManager(System.getenv("JAVA_HOME"));
            System.out.println("Подключился новый пользователь");
            while (!client.isClosed()){
                logger.log(Level.INFO,"receiving command to server");
                Command command=(Command) objectInputStream.readObject();
                if (command.getNameOfCommand().equals("exit")){
                    logger.log(Level.INFO,"disabling the server");
                    outputStream.writeUTF("Отключение сервера");
                    break;
                }
                logger.log(Level.INFO,"Sending a response to client");
                outputStream.writeUTF(serverCollectionManager.ExecuteCommand(command));
                outputStream.flush();
                if(client.isClosed()){break;}

            }
            objectInputStream.close();
            inputStream.close();
            outputStream.close();
            client.close();
        } catch (IOException e) {
            System.out.println("Невозможно создать сервер");


        }
        catch (ClassNotFoundException ex){
            System.out.println("Не удалось найти класс");
        }


    }
}
