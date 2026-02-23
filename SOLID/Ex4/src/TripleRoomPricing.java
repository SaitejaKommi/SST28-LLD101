public class TripleRoomPricing implements RoomPricing {
    @Override
    public double monthlyFee() {
        return 12000.0;
    }

    @Override
    public double deposit() {
        return 5000.0;
    }
}