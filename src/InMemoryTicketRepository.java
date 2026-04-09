import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class InMemoryTicketRepository implements TicketRepository {
    private final List<Ticket> tickets = new ArrayList<>();
    private int nextId = 1;

    @Override
    public Ticket createTicket(String customerName, String description, TicketPriority priority) {
        Ticket ticket = new Ticket(nextId++, customerName, description, priority);
        tickets.add(ticket);
        return ticket;
    }

    @Override
    public List<Ticket> getAllTickets() {
        return new ArrayList<>(tickets);
    }

    
    @Override
    public Optional<Ticket> findById(int id) {
        return tickets.stream()
                .filter(t -> t.getId() == id)
                .findFirst();
    }

    @Override
    public List<Ticket> findByStatus(TicketStatus status) {
        return tickets.stream()
                .filter(t -> t.getStatus() == status)
                .collect(Collectors.toList());
    }

    @Override
    public List<Ticket> findByPriority(TicketPriority priority) {
        return tickets.stream()
                .filter(t -> t.getPriority() == priority)
                .collect(Collectors.toList());
    }
}
