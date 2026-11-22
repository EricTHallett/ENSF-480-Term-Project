package domain;
import java.time.LocalDateTime;

public class Flight {

    public enum FlightStatus {SCHEDULED, FULL, DEPARTED, REMOVED}

    private int flightId;
    private String flightNumber;
    private String airline;
    private String origin;
    private String destination;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private double price;
    private final int totalSeats;   // assumption that the flight seats are immutable
    private int availableSeats;
    private FlightStatus status;

    public Flight(int flightId, String flightNumber, String airline, String origin, String destination,
                  LocalDateTime departureTime, LocalDateTime arrivalTime, double price, int totalSeats) {
        if (arrivalTime.isBefore(departureTime)) throw new IllegalArgumentException("Flight arrival must be after departure time");

        this.flightId = flightId;
        this.flightNumber = flightNumber;
        this.airline = airline;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.price = price;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
        this.status = FlightStatus.SCHEDULED;
    }

    // use this constructor for the FlightDAO
    public Flight(int flightId, String flightNumber, String airline, String origin, String destination,
                  LocalDateTime departureTime, LocalDateTime arrivalTime, double price, int totalSeats,
                  int availableSeats, FlightStatus status) {
        this(flightId, flightNumber, airline, origin, destination, departureTime, arrivalTime, price, totalSeats);
        if (availableSeats < 0 || availableSeats > totalSeats) throw new IllegalArgumentException("AvailableSeats must be between 0 and "+totalSeats);
        this.availableSeats = availableSeats;
        this.status = status;
    }

    public boolean isBookable() {
        return status == FlightStatus.SCHEDULED
                && departureTime.isAfter(LocalDateTime.now())
                && availableSeats > 0;
    }

    public boolean hasSeats(int count) {
        return availableSeats >= count && count > 0;
    }

    public void reserveSeats(int count) {
        if (!hasSeats(count)) throw new IllegalArgumentException("Not enough seats available");
        availableSeats -= count;
        if (availableSeats == 0) {
            status = FlightStatus.FULL;
        }
    }

    public void releaseSeats(int count) {
        if (count < 1 || availableSeats + count > totalSeats) throw new IllegalArgumentException("Invalid seat count");
        availableSeats += count;
        if (status == FlightStatus.FULL) {
            status = FlightStatus.SCHEDULED;
        }
    }

    public double calculateTotal(int count) {
        return price * count;
    }

    public void markRemoved() {
        status = FlightStatus.REMOVED; // soft delete for flights, preserves history
    }

    // admin modifies flight (reschedule or delay)
    public void updateSchedule(LocalDateTime newDeparture, LocalDateTime newArrival) {
        if (newArrival.isBefore(newDeparture)) throw new IllegalArgumentException("Arrival time must be before departure time");
        this.departureTime = newDeparture;
        this.arrivalTime = newArrival;
    }

    // admin function to change fare (?)
    public void setPrice(double newPrice) {
        if (newPrice < 0) throw new IllegalArgumentException("Price must be positive");
        this.price = newPrice;
    }



    @Override
    public String toString() {
        return "#" + flightId + " " + airline + ": " + origin + " -> " + destination
                + " | " + departureTime
                + " | $" + price
                + " | " + availableSeats + " seats left"
                + " | " + status;
    }

    public int getFlightId() {
        return flightId;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getAirline() {
        return airline;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public double getPrice() {
        return price;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public FlightStatus getStatus() {
        return status;
    }
}
