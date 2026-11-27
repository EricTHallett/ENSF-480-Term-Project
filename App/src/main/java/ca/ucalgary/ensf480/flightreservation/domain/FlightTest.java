package ca.ucalgary.ensf480.flightreservation.domain;
import java.time.LocalDateTime;

public class FlightTest {
    public static void main(String[] args) {
        LocalDateTime nextMonth = LocalDateTime.now().plusMonths(1);
        LocalDateTime yesterday = LocalDateTime.now().minusDays(1);

        Flight f = new Flight(101, "WestJet", "Calgary", "Vancouver", nextMonth, nextMonth.plusHours(4), 100.0, 3);


        //1. Brand new flight, 3 seats: expected true
        System.out.println("1. Bookable: " + f.isBookable());

        //2. Book all 3 seats: expected 0 seats left, not bookable
        f.reserveSeats(3);
        System.out.println("2. " + f);
        System.out.println("2. Bookable: " + f.isBookable());

        //3. One seat is cancelled: expect bookable again
        f.releaseSeats(1);
        System.out.println("3. Bookable: " + f.isBookable());

        //4. Request more seats than left: expted exception
        try {
            f.reserveSeats(5);
            System.out.println("4. FAIL - no exception thrown");
        }
        catch (IllegalArgumentException e) {
            System.out.println("4. Caught " + e.getMessage());
        }

        //5. Flight has already left: expect false
        Flight f2 = new Flight(102, "Air Canada", "Calgary", "Toronto", yesterday, yesterday.plusHours(4), 400.0, 10);
        System.out.println("5. Departed flight bookable: " + f2.isBookable());

        //6. Price for 2 seats at $100: expected 200.0
        System.out.println("6. Total: " + f.calculateTotal(2));
    }
}
