public class DoubleRoomPricing implements RoomPricing {
    @Override
    public double monthlyFee() {
        return 15000.0;
    }

    @Override
    public double deposit() {
        return 5000.0;
    }
}