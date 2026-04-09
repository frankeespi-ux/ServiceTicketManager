
import java.util.List;
import java.util.Optional;

public interface TicketRepository {
    Ticket createTicket(String customerName, String description, TicketPriority priority);
    List<Ticket> getAllTickets();
    Optional<Ticket> findById(int id);
    List<Ticket> findByStatus(TicketStatus status);
    List<Ticket> findByPriority(TicketPriority priority);
}