package domain;
import java.time.LocalDateTime;

public class Flight {
    private int flightId;
    private String airline;
    private String origin;
    private String destination;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private double price;
    private int totalSeats;
    private int availableSeats;

    public Flight(int flightId, String airline, String origin, String destination, LocalDateTime departureTime, LocalDateTime arrivalTime, double price, int totalSeats) {
        if (arrivalTime.isBefore(departureTime)) throw new IllegalArgumentException("Flight arrival must be after departure time");

        this.flightId = flightId;
        this.airline = airline;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.price = price;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
    }

    public boolean isBookable() {
        return departureTime.isAfter(LocalDateTime.now()) && availableSeats > 0;
    }

    public boolean hasSeats(int count) {
        return availableSeats >= count && count > 0;
    }

    public void reserveSeats(int count) {
        if (!hasSeats(count)) throw new IllegalArgumentException("Not enough seats available");
        availableSeats -= count;
    }

    public void releaseSeats(int count) {
        if (count < 0 || availableSeats + count > totalSeats) throw new IllegalArgumentException("Invalid seat count");
        availableSeats += count;
    }

    public double calculateTotal(int count) {
        return price * count;
    }

    @Override
    public String toString() {
        return "#" + flightId + " " + airline + ": " + origin + " -> " + destination
                + " | " + departureTime
                + " | $" + price
                + " | " + availableSeats + " seats left";
    }
}
