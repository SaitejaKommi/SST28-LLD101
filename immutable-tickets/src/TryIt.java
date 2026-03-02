import com.example.tickets.IncidentTicket;
import com.example.tickets.TicketService;
import java.util.ArrayList;
import java.util.List;

/**
 * Demo showing immutable ticket design with Builder pattern.
 *
 * - No direct mutation of tickets (no setters)
 * - External modifications to original tags list don't affect the ticket
 * - Service "updates" return NEW ticket instances
 */
public class TryIt {

    public static void main(String[] args) {
        TicketService service = new TicketService();

        IncidentTicket t1 = service.createTicket("TCK-1001", "reporter@example.com", "Payment failing on checkout");
        System.out.println("Created: " + t1);

        // Demonstrate immutable updates through service (returns new instance)
        IncidentTicket t2 = service.assign(t1, "agent@example.com");
        System.out.println("\nAfter assigning agent: " + t2);
        System.out.println("Original unchanged: " + t1);

        // Escalate to critical (again returns new instance)
        IncidentTicket t3 = service.escalateToCritical(t2);
        System.out.println("\nAfter escalation: " + t3);
        System.out.println("Previous version unchanged: " + t2);

        // Demonstrate that external modifications don't affect the ticket
        System.out.println("\nTesting immutability of tags list:");
        List<String> externalTags = new ArrayList<>(t3.getTags());
        externalTags.add("HACKED_FROM_OUTSIDE");
        System.out.println("External list after mutation: " + externalTags);
        System.out.println("Ticket tags unchanged: " + t3.getTags());

        // Note: Attempting t3.getTags().add(...) would throw UnsupportedOperationException
        // because getTags() returns an unmodifiable list
    }
}