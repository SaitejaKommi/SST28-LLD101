import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Single shared store — both invoices land in the same in-memory store.
        InvoiceStore store = new FileStore();
        CafeteriaSystem sys = new CafeteriaSystem(store);

        sys.addToMenu(new MenuItem("M1", "Veg Thali", 80.00));
        sys.addToMenu(new MenuItem("C1", "Coffee", 30.00));
        sys.addToMenu(new MenuItem("S1", "Sandwich", 60.00));

        // --- Student invoice (unchanged from original output) ---
        System.out.println("=== Cafeteria Billing ===");
        List<OrderLine> studentOrder = List.of(
                new OrderLine("M1", 2),
                new OrderLine("C1", 1));
        sys.checkout(new StudentTaxPolicy(), new StudentDiscountPolicy(), studentOrder);

        // --- Stretch goal: Staff invoice with a different policy ---
        // Adding a new customer type required ZERO changes to CafeteriaSystem.
        System.out.println("\n=== Staff Billing ===");
        List<OrderLine> staffOrder = List.of(
                new OrderLine("M1", 1),
                new OrderLine("C1", 1),
                new OrderLine("S1", 1));
        sys.checkout(new StaffTaxPolicy(), new StaffDiscountPolicy(), staffOrder);
    }
}