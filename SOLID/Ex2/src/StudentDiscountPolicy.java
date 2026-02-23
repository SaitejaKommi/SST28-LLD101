public class StudentDiscountPolicy implements DiscountPolicy {
    @Override
    public double discountAmount(double subtotal, int lineCount) {
        return subtotal >= 180.0 ? 10.0 : 0.0;
    }
}