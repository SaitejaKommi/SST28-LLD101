import java.util.*;

/**
 * SRP: CafeteriaSystem orchestrates only.
 * It does NOT know tax rates, discount rules, or how an invoice looks.
 * Those concerns are owned by TaxPolicy, DiscountPolicy, and InvoiceFormatter.
 */
public class CafeteriaSystem {
    private final Map<String, MenuItem> menu = new LinkedHashMap<>();
    private final InvoiceStore store;
    private final InvoiceFormatter formatter = new InvoiceFormatter();
    private int invoiceSeq = 1000;

    public CafeteriaSystem(InvoiceStore store) {
        this.store = store;
    }

    public void addToMenu(MenuItem item) {
        menu.put(item.id, item);
    }

    public void checkout(TaxPolicy taxPolicy, DiscountPolicy discountPolicy, List<OrderLine> lines) {
        String invId = "INV-" + (++invoiceSeq);

        // 1. Compute line totals
        List<String> itemLines = new ArrayList<>();
        double subtotal = 0.0;
        for (OrderLine l : lines) {
            MenuItem item = menu.get(l.itemId);
            double lineTotal = item.price * l.qty;
            subtotal += lineTotal;
            itemLines.add(String.format("- %s x%d = %.2f", item.name, l.qty, lineTotal));
        }

        double taxPct = taxPolicy.taxPercent();
        double taxAmt = subtotal * (taxPct / 100.0);
        double discAmt = discountPolicy.discountAmount(subtotal, lines.size());
        double total = subtotal + taxAmt - discAmt;

        String invoice = formatter.format(invId, itemLines, subtotal, taxPct, taxAmt, discAmt, total);

        System.out.print(invoice);
        store.save(invId, invoice);
        System.out.println("Saved invoice: " + invId + " (lines=" + store.countLines(invId) + ")");
    }
}