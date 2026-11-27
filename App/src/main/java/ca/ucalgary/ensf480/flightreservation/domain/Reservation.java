package ca.ucalgary.ensf480.flightreservation.domain;

public class Reservation {
    private final User passenger;
    private final Flight flight;
    private int numSeats;
    private int priceCents;
    private ReservationStatus status;
    private final PaymentStrategy paymentStrategy;

    public Reservation(User passenger, Flight flight, int numSeats, ReservationStatus status, PaymentStrategy paymentStrategy) {
        if (!flight.isBookable()) throw new IllegalStateException("Flight cannot be booked.");
        flight.reserveSeats(numSeats);

        this.passenger = passenger;
        this.flight = flight;
        this.numSeats = numSeats;
        this.priceCents = priceFor(flight, numSeats);
        this.status = status;
        this.paymentStrategy = paymentStrategy;
    }

    public Reservation(User passenger, Flight flight, int numSeats, int priceCents,
                       ReservationStatus status, PaymentStrategy paymentStrategy) {
        this.passenger = passenger;
        this.flight = flight;
        this.numSeats = numSeats;
        this.priceCents = priceCents;
        this.status = status;
        this.paymentStrategy = paymentStrategy;
    }

    private static int priceFor(Flight flight, int seats) {
        return (int) Math.round(flight.calculateTotal(seats) * 100);
    }

    public User getPassenger() {
        return passenger;
    }

    public Flight getFlight() {
        return flight;
    }

    public int getNumSeats() {
        return numSeats;
    }

    public void addSeats(int count) {
        flight.reserveSeats(count);
        numSeats += count;
        priceCents = priceFor(flight, numSeats);
    }

    public void removeSeats(int count) {
        if (numSeats - count <= 0) throw new IllegalArgumentException("Reservation must have at least one seat");

        flight.releaseSeats(count);
        numSeats -= count;
        priceCents = priceFor(flight, numSeats);
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

	public PaymentStrategy getPaymentStrategy() {
		return paymentStrategy;
	}

}
