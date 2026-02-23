import java.util.*;

public class HostelFeeCalculator {
    private final FakeBookingRepo repo;

    public HostelFeeCalculator(FakeBookingRepo repo) {
        this.repo = repo;
    }

    public void process(BookingRequest req,
            RoomPricing roomPricing,
            List<AddOnPricing> addOnPricings) {


        double addOnTotal = 0.0;
        for (AddOnPricing a : addOnPricings) {
            addOnTotal += a.monthlyFee();
        }

        Money monthly = new Money(roomPricing.monthlyFee() + addOnTotal);
        Money deposit = new Money(roomPricing.deposit());

        ReceiptPrinter.print(req, monthly, deposit);

        String bookingId = "H-" + (7000 + new Random(1).nextInt(1000));
        repo.save(bookingId, req, monthly, deposit);
    }
}