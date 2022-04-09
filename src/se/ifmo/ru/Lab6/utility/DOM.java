package se.ifmo.ru.Lab6.utility;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import se.ifmo.ru.Lab6.data.*;
import se.ifmo.ru.Lab6.exception.ElementMustNotBeEmptyException;
import se.ifmo.ru.Lab6.exception.InvalidElementValueException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;


public class DOM {
    /**
     * Свойство - список, в которой записывается считанные данные
     */
    private LinkedList<Ticket> tickets;
    private ArrayList<String> valueOfTicket;

    /**
     * Конструктор
     * @param tickets Список, в который записываются данные
     */
    public DOM(LinkedList<Ticket> tickets){
        this.tickets=tickets;
        valueOfTicket=new ArrayList<>();
    }

    /**
     * Метод - читает файл
     * @throws ParserConfigurationException немозможно считать файл
     * @throws IOException Файл отсутствует
     * @throws SAXException Непредвиденная ошибка
     */
    public void readingFile(String env) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory documentBuilderFactory=DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder=documentBuilderFactory.newDocumentBuilder();
        Document document=documentBuilder.parse(new File("input.xml"));
        NodeList nodeList=document.getDocumentElement().getElementsByTagName("Ticket");
        for (int i=0;i<nodeList.getLength();i++){
            Node oneel=nodeList.item(i);
            printInfoAboutAllChildNodes(oneel.getChildNodes());
            LocalDate localDate= LocalDate.of(Integer.parseInt(valueOfTicket.get(4).substring(6)),Integer.parseInt(valueOfTicket.get(4).substring(3,5)),Integer.parseInt(valueOfTicket.get(4).substring(0,2)));
            try {
                for(String string: valueOfTicket){
                    if (string.equals("")){
                        throw new ElementMustNotBeEmptyException();
                    }
                }
                if(Long.parseLong(valueOfTicket.get(0))<=0||Float.parseFloat(valueOfTicket.get(5))<=0||Long.parseLong(valueOfTicket.get(3))<=-132||valueOfTicket.get(16).length()>=272||Integer.parseInt(valueOfTicket.get(10))<=0) {
                    throw new InvalidElementValueException();
                }
                tickets.add(new Ticket(Long.parseLong(valueOfTicket.get(0)),valueOfTicket.get(1),
                        new Coordinates(Float.parseFloat(valueOfTicket.get(2)),Long.parseLong(valueOfTicket.get(3))),
                        localDate,Float.parseFloat(valueOfTicket.get(5)),Boolean.parseBoolean(valueOfTicket.get(6)),
                        TicketType.valueOf(valueOfTicket.get(7)),new Venue(Long.parseLong(valueOfTicket.get(8)),valueOfTicket.get(9), Integer.parseInt(valueOfTicket.get(10)),
                        new Address(valueOfTicket.get(11), valueOfTicket.get(12),
                                new Location(Long.parseLong(valueOfTicket.get(13)),Float.parseFloat(valueOfTicket.get(14)),Long.parseLong(valueOfTicket.get(15)), valueOfTicket.get(16)) ))));
            }
            catch (NumberFormatException ex){
                System.out.println("Неверный формат вводна данных, данные искажены");
            }
            catch (IllegalArgumentException ex){
                System.out.println("Неверный тип Билета");
            }
            catch (ElementMustNotBeEmptyException ex){
                System.out.println("Некоторые данные пусты");
            }
            catch (IndexOutOfBoundsException ex){
                System.out.println("Недостаточно данных, данные будут искажены");
            }
            catch (InvalidElementValueException ex){
                System.out.println("Часть данных не соответствует условиям, данные будут искажены ");
            }

            valueOfTicket.clear();
        }

    }

    /**
     * Метод - считывает из list файлы
     * @param list - хранит считанные файлы
     */
    private void printInfoAboutAllChildNodes(NodeList list){
        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);
            if (node.getNodeType() == Node.TEXT_NODE) {
                String textInformation = node.getNodeValue().replace("\n", "").trim();
                if (!textInformation.isEmpty())
                    valueOfTicket.add(node.getNodeValue());
            }
            if (node.hasChildNodes())
                printInfoAboutAllChildNodes(node.getChildNodes());
        }
    }
}
