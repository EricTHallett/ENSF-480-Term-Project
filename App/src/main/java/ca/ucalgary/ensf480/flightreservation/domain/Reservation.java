package ca.ucalgary.ensf480.flightreservation.domain;

public class Reservation {
    private final User passenger;
    private final Flight flight;
    private int numSeats;
    private int priceCents;
    private ReservationStatus status;

    public Reservation(User passenger, Flight flight, int numSeats, ReservationStatus status) {
        if (!flight.isBookable()) throw new IllegalStateException("Flight cannot be booked.");
        flight.reserveSeats(numSeats);

        this.passenger = passenger;
        this.flight = flight;
        this.numSeats = numSeats;
        this.priceCents = flight.calculateTotal() * 100;
        this.status = status;
    }

    public User getPassenger() {
        return passenger;
    }

    public int getFlight() {
        return flight;
    }

    public int getNumSeats() {
        return numSeats;
    }

    public void addSeats(int count) {
        flight.reserveSeats(count);
        numSeats += count;
    }

    public void removeSeats(int count) {
        if (numSeats - count <= 0) throw new IllegalArgumentException("Reservation must have at least one seat");

        flight.releaseSeats(count);
        numSeats -= count;
    }

    public int getPriceCents() {
        return priceCents;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

}
