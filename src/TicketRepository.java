import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TicketRepository {
    private final List<Ticket> tickets = new ArrayList<>();
    private int nextId = 1;

    public Ticket createTicket(String customerName, String description, TicketPriority priority) {
        Ticket ticket = new Ticket(nextId++, customerName, description, priority);
        tickets.add(ticket);
        return ticket;
    }

    public List<Ticket> getAllTickets() {
        return new ArrayList<>(tickets);
    }

    public Optional<Ticket> findById(int id) {
        return tickets.stream()
                .filter(t -> t.getId() == id)
                .findFirst();
    }

    public List<Ticket> findByStatus(TicketStatus status) {
        return tickets.stream()
                .filter(t -> t.getStatus() == status)
                .collect(Collectors.toList());
    }
}
