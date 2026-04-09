import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Ticket {
    private final int id;
    private String customerName;
    private String description;
    private TicketPriority priority;
    private TicketStatus status;
    private final LocalDateTime createdAt;

    private final List<String> auditLog = new ArrayList<>();
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public Ticket(int id, String customerName, String description,
                  TicketPriority priority) {
        this.id = id;
        this.customerName = customerName;
        this.description = description;
        this.priority = priority;
        this.status = TicketStatus.OPEN;
        this.createdAt = LocalDateTime.now();

        this.auditLog.add(this.createdAt.format(FORMATTER) + " - Ticket created as OPEN");
    }

    //New:Log the status change before actually changing it
    public void advanceStatus(TicketStatus newStatus) {
        if (this.status == TicketStatus.CLOSED) {
            throw new IllegalStateException("Cannot change status of a CLOSED ticket.");
        }
        String logEntry = LocalDateTime.now().format(FORMATTER) + " - Status changed from " + this.status + " to " + newStatus;
        this.auditLog.add(logEntry);

        //actually updates the status after logging
        this.status = newStatus;
    }

    public int getId() {
        return id;
    }

    public List<String> getAuditlog() {
        //returning a copy of the list is a best practice to prevent external modification of the internal state
        //to prevent outside code from modifying the internal audit log, we return a new list that contains the same entries. This way, the original audit log inside the Ticket class remains unchanged and protected from unintended side effects.
        return new ArrayList<>(auditLog);
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getDescription() {
        return description;
    }

    public TicketPriority getPriority() {
        return priority;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPriority(TicketPriority priority) {
        this.priority = priority;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Ticket {" +
                "id=" + id +
                ", customerName='" + customerName + '\'' +
                ", description='" + description + '\'' +
                ", priority=" + priority +
                ", status=" + status +
                ", createdAt=" + createdAt +
                '}';
    }
}
