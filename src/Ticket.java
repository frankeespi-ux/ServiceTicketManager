import java.time.LocalDateTime;

public class Ticket {
    private final int id;
    private String customerName;
    private String description;
    private TicketPriority priority;
    private TicketStatus status;
    private final LocalDateTime createdAt;

    public Ticket(int id, String customerName, String description,
                  TicketPriority priority) {
        this.id = id;
        this.customerName = customerName;
        this.description = description;
        this.priority = priority;
        this.status = TicketStatus.OPEN;
        this.createdAt = LocalDateTime.now();
    }

    public int getId() {
        return id;
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
