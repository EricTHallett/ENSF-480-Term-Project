package domain;
import java.time.LocalDateTime;

public class FlightTest {
    public static void main(String[] args) {
        LocalDateTime nextMonth = LocalDateTime.now().plusMonths(1);
        LocalDateTime yesterday = LocalDateTime.now().minusDays(1);

        Flight f = new Flight(101, "WS101", "WestJet", "Calgary", "Vancouver", nextMonth, nextMonth.plusHours(4), 100.0, 3);


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
        Flight f2 = new Flight(102, "AC102", "Air Canada", "Calgary",
                "Toronto", yesterday, yesterday.plusHours(4), 400.0, 10);
        System.out.println("5. Departed flight bookable: " + f2.isBookable());

        //6. Price for 2 seats at $100: expected 200.0
        System.out.println("6. Total: " + f.calculateTotal(2));

        //7. Admin removes the flight: expect false
        f.markRemoved();
        System.out.println("7. Removed flight bookable: " + f.isBookable());

        //8. Rebuild a saved flight: 3 seats left, not FULL, but SCHEDULED\
        Flight saved = new Flight(103, "WS200", "WestJet", "Calgary", "Toronto",
                nextMonth, nextMonth.plusHours(4), 250.0, 10, 3, Flight.FlightStatus.SCHEDULED);
        System.out.println("8. Departed flight bookable: " + saved.isBookable());

        //9. Negative price: expect exception
        try {
            saved.setPrice(-50);
            System.out.println("9. FAIL - no exception thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("9. Caught " + e.getMessage());
        }

        //10. Reschedule with arrival before departure: expect exception
        try {
            saved.updateSchedule(nextMonth, nextMonth.minusHours(2));
            System.out.println("10. FAIL - no exception thrown");
        } catch (IllegalArgumentException e) {
            System.out.println("10. Caught " + e.getMessage());
        }

        //11. Valid 2-hour delay: expect new departure time
        saved.updateSchedule(nextMonth.plusHours(2), nextMonth.plusHours(6));
        System.out.println("11. New departure: " + saved.getDepartureTime());

        //12. Past flight: departed and arrived, then marked DEPARTED
        System.out.println("12. Departed: " + f2.hasDeparted() + ", arrived: " + f2.hasArrived());
        f2.markDeparted();
        System.out.println("12. " + f2);

        //13. Future flight: markDeparted does nothing
        saved.markDeparted();
        System.out.println("13. " + saved.getStatus());

        //14. New flight gets its database id the first time
        Flight fresh = new Flight (0, "WS300", "WestJet", "Calgary", "Edmonton", nextMonth, nextMonth.plusHours(1), 150.0, 50);
        fresh.assignId(42);
        System.out.println("14. Id: " + fresh.getFlightId());

        //15. Assigning another id: expect exception
        try {
            fresh.assignId(43);
            System.out.println("15. FAIL - no exception thrown");
        } catch (IllegalStateException e) {
            System.out.println("15. Caught " + e.getMessage());
        }

        //16. Editing a removed flight: expect eception
        try{
            f.setPrice(120.0);
            System.out.println("16. FAIL - no exception thrown");
        } catch (IllegalStateException e) {
            System.out.println("16. Caught " + e.getMessage());
        }


    }

}
