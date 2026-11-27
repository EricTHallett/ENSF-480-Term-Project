package ca.ucalgary.ensf480.flightreservation.domain;

public enum ReservationStatus {
    RESERVED,   // Seats are reserved but flight has not yet flown
    FULFILLED,  // Seats are reserved and flight has been flown
    CANCELLED,  // Reservation was cancelled before the flight was flown
}
