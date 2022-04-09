package se.ifmo.ru.Lab6.Commands.CommandsForCollection;

import se.ifmo.ru.Lab6.Commands.Command;
import se.ifmo.ru.Lab6.data.Ticket;
import se.ifmo.ru.Lab6.data.Venue;

import java.time.LocalDate;
import java.util.LinkedList;

public class AddIfMin extends Command {
    private Ticket ticket;
    public AddIfMin(String nameOfCommand) {
        super(nameOfCommand);
    }
    @Override
    public String ExecuteCommand(LinkedList<Ticket> tickets) {
        if (tickets.size() == 0) {
            tickets.add(ticket);
            return "Коллекция пуста, добавление будет выполнено";

        } else {
            float price;
            price = tickets.get(0).getPrice();
            for (Ticket ticket1 : tickets) {
                if ( ticket1.getPrice()<price) {
                    price = ticket1.getPrice();
                }

            }
            if (ticket.getPrice()<price) {
                tickets.add(ticket);
                return "Элемент успешно добавлен";

            } else return "Элемент не наименьший";

        }
    }
    @Override
    public String InfoOfCommand(){
        return "Добавляет в коллекцию новый элемент, если его значение наименьшее";
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}
