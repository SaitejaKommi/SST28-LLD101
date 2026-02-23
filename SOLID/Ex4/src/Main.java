import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Hostel Fee Calculator ===");
        RoomPricing room = new DoubleRoomPricing();

        List<AddOnPricing> addOns = new ArrayList<>();
        addOns.add(new LaundryPricing());
        addOns.add(new MessPricing());

        BookingRequest req = new BookingRequest(LegacyRoomTypes.DOUBLE, List.of(AddOn.LAUNDRY, AddOn.MESS));
        HostelFeeCalculator calc = new HostelFeeCalculator(new FakeBookingRepo());
        calc.process(req, room, addOns);

       
        System.out.println("\n=== Hostel Fee Calculator (with Late Fee) ===");
        List<AddOnPricing> addOnsLate = new ArrayList<>();
        addOnsLate.add(new LaundryPricing());
        addOnsLate.add(new MessPricing());
        addOnsLate.add(new LateFee()); 

        BookingRequest req2 = new BookingRequest(LegacyRoomTypes.DOUBLE,
                List.of(AddOn.LAUNDRY, AddOn.MESS));
        calc.process(req2, room, addOnsLate);
    }
}