public class StaffDiscountPolicy implements DiscountPolicy {
    @Override
    public double discountAmount(double subtotal, int lineCount) {
        return lineCount >= 3 ? 15.0 : 5.0;
    }
}