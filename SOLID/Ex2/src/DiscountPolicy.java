/**
 * SRP: One reason to change — the discount calculation rule.
 * Each implementation owns one customer-type's discount logic.
 */
public interface DiscountPolicy {
    /**
     * @param subtotal  order subtotal before tax
     * @param lineCount number of distinct order lines
     */
    double discountAmount(double subtotal, int lineCount);
}