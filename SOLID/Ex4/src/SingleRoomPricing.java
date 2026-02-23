public class SingleRoomPricing implements RoomPricing {
    @Override
    public double monthlyFee() {
        return 14000.0;
    }

    @Override
    public double deposit() {
        return 5000.0;
    }
}