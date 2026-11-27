package ca.ucalgary.ensf480.flightreservation.domain;
import java.time.LocalDateTime;

public class Flight {

    public enum FlightStatus {SCHEDULED, FULL, DEPARTED, REMOVED}

    private int flightId;
    private String airline;
    private String origin;
    private String destination;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private double price;
    private final int totalSeats;   // assumption that the flight seats are immutable
    private int availableSeats;
    private FlightStatus status;

    public Flight(int flightId, String airline, String origin, String destination,
                  LocalDateTime departureTime, LocalDateTime arrivalTime, double price, int totalSeats) {
        requireText(airline, "Airline");
        requireText(origin, "Origin");
        requireText(destination, "Destination");
        if (origin.equalsIgnoreCase(destination)) throw new IllegalArgumentException("Origin and destination cannot be the same");
        if (departureTime == null || arrivalTime == null) throw new IllegalArgumentException("Departure or arrival time cannot be null");
        if (arrivalTime.isBefore(departureTime)) throw new IllegalArgumentException("Arrival time cannot be before departure time");
        if (price < 0) throw new IllegalArgumentException("Price cannot be negative");
        if (totalSeats < 1) throw new IllegalArgumentException("Total seats must be at least 1");

        this.flightId = flightId;
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
    public Flight(int flightId, String airline, String origin, String destination,
                  LocalDateTime departureTime, LocalDateTime arrivalTime, double price, int totalSeats,
                  int availableSeats, FlightStatus status) {
        this(flightId, airline, origin, destination, departureTime, arrivalTime, price, totalSeats);
        if (availableSeats < 0 || availableSeats > totalSeats) throw new IllegalArgumentException("AvailableSeats must be between 0 and "+totalSeats);
        this.availableSeats = availableSeats;
        this.status = status;
    }

    public boolean isBookable() {
        return status == FlightStatus.SCHEDULED
                && !hasDeparted()
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

    public void markDeparted() {
        if (status != FlightStatus.REMOVED && hasDeparted()) {
            status = FlightStatus.DEPARTED;
        }
    }

    // use with DAO when insertinng a new flight, stores the ID from database
    public void assignId(int id) {
        if (flightId != 0) throw new IllegalArgumentException("Flight already assigned an id");
        if (id < 1)  throw new IllegalArgumentException("Flight id must be positive");
        this.flightId = id;
    }

    public int getId() {
        return flightId;
    }

    // admin modifies flight (reschedule or delay)
    public void updateSchedule(LocalDateTime newDeparture, LocalDateTime newArrival) {
        requireEditable();
        if (newArrival.isBefore(newDeparture)) throw new IllegalArgumentException("Departure time must be before arrival time");
        this.departureTime = newDeparture;
        this.arrivalTime = newArrival;
    }

    // admin function to change fare (?)
    public void setPrice(double newPrice) {
        requireEditable();
        if (newPrice < 0) throw new IllegalArgumentException("Price must not be negative");
        this.price = newPrice;
    }

    public boolean hasDeparted() {
        return !departureTime.isAfter(LocalDateTime.now());
    }

    public boolean hasArrived() {
        return !arrivalTime.isAfter(LocalDateTime.now());
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

    // checker if text is missing when building
    private static void requireText(String value, String fieldName) {
        if (value == null || value.isBlank()) throw new IllegalArgumentException(fieldName + " must not be blank");
    }

    private void requireEditable() {
        if (status == FlightStatus.REMOVED || status == FlightStatus.DEPARTED) throw new IllegalArgumentException("Cannot modify REMOVED or DEPARTED flights");
    }
}
