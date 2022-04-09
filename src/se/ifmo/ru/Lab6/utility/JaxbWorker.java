package se.ifmo.ru.Lab6.utility;

import se.ifmo.ru.Lab6.data.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

/**
 * Класс, сохраняет коллекцию
 */
public class JaxbWorker {
    private TicketList tickets;
    private String name;

    public JaxbWorker(TicketList tickets, String name) {
        this.tickets = tickets;
        this.name = name;
    }


    public void convertObjectToXml(){
        try {
            JAXBContext context = JAXBContext.newInstance(TicketList.class);
            Marshaller marshaller = context.createMarshaller();
            // устанавливаем флаг для читабельного вывода XML в JAXB
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            // маршаллинг объекта в файл
            marshaller.marshal(tickets, new File(name));
        } catch (JAXBException e) {
            System.out.println("2");
        }
    }
}
