package ca.ucalgary.ensf480.flightreservation.database;

import java.util.Arrays;

public final class DatabaseCallbacks {
    private DatabaseCallbacks() {}

    // Flights

    public static void searchFlights(String[] args) {
        System.out.println("DatabaseCallbacks.searchFlights: " + Arrays.toString(args));
    }

    public static void addFlight(String[] args) throws IllegalArgumentException {
        throw new IllegalArgumentException("This is a test");
        //System.out.println("DatabaseCallbacks.addFlight: " + Arrays.toString(args));
    }

    public static void removeFlight(String[] args) {
        System.out.println("DatabaseCallbacks.removeFlight: " + Arrays.toString(args));
    }

    public static void updateFlight(String[] args) {
        System.out.println("DatabaseCallbacks.updateFlight: " + Arrays.toString(args));
    }

    public static void cancelFlight(String[] args) {
        System.out.println("DatabaseCallbacks.cancelFlight: " + Arrays.toString(args));
    }

    public static Object[][] listFlights() {
        // Placeholder data until a SELECT query is implemented.
        return new Object[][] {
            {1, "Calgary", "Toronto", "ACTIVE"},
            {2, "Calgary", "Vancouver", "ACTIVE"},
            {3, "Edmonton", "Montreal", "CANCELLED"}
        };
    }

    // Reservations

    public static void searchReservations(String[] args) {
        System.out.println("DatabaseCallbacks.searchReservations: " + Arrays.toString(args));
    }

    public static void viewReservation(String[] args) {
        System.out.println("DatabaseCallbacks.viewReservation: " + Arrays.toString(args));
    }

    public static void cancelReservation(String[] args) {
        System.out.println("DatabaseCallbacks.cancelReservation: " + Arrays.toString(args));
    }

    public static void removeReservation(String[] args) {
        System.out.println("DatabaseCallbacks.removeReservation: " + Arrays.toString(args));
    }

    public static Object[][] listReservations() {
        // Placeholder data until a search query is implemented.
        return new Object[][] {
            {1, 1, 1, "CONFIRMED"},
            {2, 2, 1, "CANCELLED"},
            {3, 1, 2, "CONFIRMED"}
        };
    }

    // Customers

    public static void searchCustomers(String[] args) {
        System.out.println("DatabaseCallbacks.searchCustomers: " + Arrays.toString(args));
    }

    public static void addCustomer(String[] args) throws IllegalArgumentException {
        System.out.println("DatabaseCallbacks.addCustomer: " + Arrays.toString(args));
    }

    public static void viewCustomer(String[] args) {
        System.out.println("DatabaseCallbacks.viewCustomer: " + Arrays.toString(args));
    }

    public static void deactivateCustomer(String[] args) {
        System.out.println("DatabaseCallbacks.deactivateCustomer: " + Arrays.toString(args));
    }

    public static void reactivateCustomer(String[] args) {
        System.out.println("DatabaseCallbacks.reactivateCustomer: " + Arrays.toString(args));
    }

    public static void deleteCustomer(String[] args) {
        System.out.println("DatabaseCallbacks.deleteCustomer: " + Arrays.toString(args));
    }

    public static Object[][] listCustomers() {
        // Placeholder data until a search query is implemented.
        return new Object[][] {
            {1, "CUSTOMER", "ACTIVE"},
            {2, "CUSTOMER", "ACTIVE"},
            {3, "AGENT", "ACTIVE"},
            {4, "ADMIN", "ACTIVE"}
        };
    }

    // Aircraft

    public static void searchAircraft(String[] args) {
        System.out.println("DatabaseCallbacks.searchAircraft: " + Arrays.toString(args));
    }

    public static void addAircraft(String[] args) throws IllegalArgumentException {
        System.out.println("DatabaseCallbacks.addAircraft: " + Arrays.toString(args));
    }

    public static void viewAircraft(String[] args) {
        System.out.println("DatabaseCallbacks.viewAircraft: " + Arrays.toString(args));
    }

    public static void deactivateAircraft(String[] args) {
        System.out.println("DatabaseCallbacks.deactivateAircraft: " + Arrays.toString(args));
    }

    public static void reactivateAircraft(String[] args) {
        System.out.println("DatabaseCallbacks.reactivateAircraft: " + Arrays.toString(args));
    }

    public static void deleteAircraft(String[] args) {
        System.out.println("DatabaseCallbacks.deleteAircraft: " + Arrays.toString(args));
    }

    public static Object[][] listAircraft() {
        // Placeholder data until a SELECT query is implemented.
        return new Object[][] {
            {1, "ACTIVE"},
            {2, "INACTIVE"}
        };
    }

    // Bottom navigation operations

    public static void viewUserInfo() {
        System.out.println("DatabaseCallbacks.viewUserInfo");
    }

    public static void sendPromotion() {
        System.out.println("DatabaseCallbacks.sendPromotion");
    }
}
