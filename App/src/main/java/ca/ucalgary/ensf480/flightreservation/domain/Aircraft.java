package ca.ucalgary.ensf480.flightreservation.domain;

public class Aircraft {
    private int id;
    private final String model;
    private final int seats;
    private boolean isActive;

    public Aircraft(int id, String model, int seats, boolean isActive) {
        if (model == null || model.isBlank()) throw new IllegalArgumentException("Model must not be blank");
        if (seats < 1) throw new IllegalArgumentException("Seats must be at least 1");
        this.id = id;
        this.model = model;
        this.seats = seats;
        this.isActive = isActive;
    }

    public void assignId(int id) {
        if (this.id != 0) throw new IllegalStateException("Aircraft already has an id");
        this.id = id;
    }

    public int getId() { return id; }
    public String getModel() { return model; }
    public int getSeats() { return seats; }
    public boolean isActive() { return isActive; }
    public void setActive(boolean isActive) { this.isActive = isActive; }
}
