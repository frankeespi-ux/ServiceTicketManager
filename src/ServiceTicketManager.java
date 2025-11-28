import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ServiceTicketManager {

    private final TicketRepository repository;
    private final Scanner scanner;

    public ServiceTicketManager() {
        this.repository = new TicketRepository();
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        ServiceTicketManager app = new ServiceTicketManager();
        app.run();
    }

    public void run() {
        boolean running = true;

        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> createTicket();
                case "2" -> listAllTickets();
                case "3" -> filterTicketsByStatus();
                case "4" -> updateTicketStatus();
                case "0" -> {
                    System.out.println("Exiting. Goodbye.");
                    running = false;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void printMenu() {
        System.out.println();
        System.out.println("=== Service Ticket Manager ===");
        System.out.println("1. Create new ticket");
        System.out.println("2. List all tickets");
        System.out.println("3. Filter tickets by status");
        System.out.println("4. Update ticket status");
        System.out.println("0. Exit");
        System.out.print("Enter choice: ");
    }

    private void createTicket() {
        System.out.print("Customer name: ");
        String customerName = scanner.nextLine().trim();

        System.out.print("Description: ");
        String description = scanner.nextLine().trim();

        System.out.print("Priority (LOW, MEDIUM, HIGH): ");
        TicketPriority priority = readPriority();

        Ticket ticket = repository.createTicket(customerName, description, priority);
        System.out.println("Created ticket: " + ticket);
    }

    private TicketPriority readPriority() {
        while (true) {
            String input = scanner.nextLine().trim().toUpperCase();
            try {
                return TicketPriority.valueOf(input);
            } catch (IllegalArgumentException ex) {
                System.out.print("Invalid priority. Enter LOW, MEDIUM, or HIGH: ");
            }
        }
    }

    private void listAllTickets() {
        List<Ticket> tickets = repository.getAllTickets();
        if (tickets.isEmpty()) {
            System.out.println("No tickets found.");
        } else {
            tickets.forEach(System.out::println);
        }
    }

    private void filterTicketsByStatus() {
        System.out.print("Status (OPEN, IN_PROGRESS, CLOSED): ");
        TicketStatus status = readStatus();
        List<Ticket> tickets = repository.findByStatus(status);
        if (tickets.isEmpty()) {
            System.out.println("No tickets with status " + status + ".");
        } else {
            tickets.forEach(System.out::println);
        }
    }

    private TicketStatus readStatus() {
        while (true) {
            String input = scanner.nextLine().trim().toUpperCase();
            try {
                return TicketStatus.valueOf(input);
            } catch (IllegalArgumentException ex) {
                System.out.print("Invalid status. Enter OPEN, IN_PROGRESS, or CLOSED: ");
            }
        }
    }

    private void updateTicketStatus() {
        System.out.print("Enter ticket ID: ");
        String idText = scanner.nextLine().trim();
        int id;

        try {
            id = Integer.parseInt(idText);
        } catch (NumberFormatException ex) {
            System.out.println("Invalid ID. Must be a number.");
            return;
        }

        Optional<Ticket> optionalTicket = repository.findById(id);
        if (optionalTicket.isEmpty()) {
            System.out.println("Ticket not found for ID: " + id);
            return;
        }

        Ticket ticket = optionalTicket.get();
        System.out.println("Current ticket: " + ticket);
        System.out.print("New status (OPEN, IN_PROGRESS, CLOSED): ");
        TicketStatus newStatus = readStatus();
        ticket.setStatus(newStatus);
        System.out.println("Updated ticket: " + ticket);
    }
}
