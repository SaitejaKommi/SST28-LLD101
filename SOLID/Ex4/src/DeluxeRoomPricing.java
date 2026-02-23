public class DeluxeRoomPricing implements RoomPricing {
    @Override
    public double monthlyFee() {
        return 16000.0;
    }

    @Override
    public double deposit() {
        return 5000.0;
    }
}