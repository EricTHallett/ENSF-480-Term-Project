package ca.ucalgary.ensf480.flightreservation.ui.common;

public enum ModuleType {
    FLIGHTS("Flights"),
    RESERVATIONS("Reservations"),
    CUSTOMERS("Customers"),
    ROUTES("Routes"),
    AIRCRAFT("Aircraft");

    private final String displayName;

    ModuleType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
