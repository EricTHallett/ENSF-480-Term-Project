package ca.ucalgary.ensf480.flightreservation.database;

import java.sql.Connection;
import java.util.Arrays;

import ca.ucalgary.ensf480.flightreservation.domain.UserRole;

public final class DatabaseCallbacks {

    private static final Connection DB = DatabaseManager.getInstance().getConnection();
    private static final FlightDAO FLIGHTS = new FlightDAO(DB);
    private static final ReservationDAO RESERVATIONS = new ReservationDAO(DB);
    private static final UserDAO USERS = new UserDAO(DB);
    private static final AircraftDAO AIRCRAFT = new AircraftDAO(DB);

    private DatabaseCallbacks() {}

    // Flights

    public static Object[][] searchFlight(String[] args) {
        System.out.println("DatabaseCallbacks.searchFlights: " + Arrays.toString(args));
        return new Object[0][0];
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
        return FLIGHTS.findAll().stream()
                .map(f -> new Object[]{f.getId(), f.getOrigin(), f.getDestination(), f.getStatus().name()})
                .toArray(Object[][]::new);
    }

    // Reservations

    public static Object[][] searchReservation(String[] args) {
        System.out.println("DatabaseCallbacks.searchReservations: " + Arrays.toString(args));
        return new Object[0][0];
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
        return RESERVATIONS.findAll().stream()
                .map(r -> new Object[]{r.getPassenger().getEmail(), r.getFlight().getId(), r.getStatus().name()})
                .toArray(Object[][]::new);
    }

    // Customers
    public static Object[][] searchCustomers(String[] args) {
        String sql = """
                SELECT * FROM User
                WHERE email LIKE ?
                AND role LIKE ?
                AND first_name LIKE ?
                AND last_name LIKE ?
                AND CASE WHEN is_active = 1
                        THEN 'true' ELSE 'false' END LIKE ?
                AND COALESCE(promotion_sent_on, '') LIKE ?
                """;


        ArrayList<Object[]> rows = new ArrayList<>();
        Connection connection = DatabaseManager.getInstance().getConnection();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            for (int i = 0; i < args.length; i++) {
                stmt.setString(i + 1, "%" + args[i].trim() + "%");
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    rows.add(new Object[]{
                        rs.getString("email"),
                        rs.getString("role"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getBoolean("is_active"),
                        rs.getString("promotion_sent_on")
                    });
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve customers", e);
        }

        return rows.toArray(new Object[0][]);
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
        return USERS.findAll().stream().filter(u -> u.getRole() == UserRole.CUSTOMER)
                .map(u -> new Object[]{u.getEmail(), u.getRole().name(), u.isActive() ? "ACTIVE" : "INACTIVE"})
                .toArray(Object[][]::new);
    }

    // Aircraft

    public static Object[][] searchAircraft(String[] args) {
        System.out.println("DatabaseCallbacks.searchAircraft: " + Arrays.toString(args));
        return new Object[0][0];
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
        return AIRCRAFT.findAll().stream()
                .map(a -> new Object[]{a.getId(), a.isActive() ? "ACTIVE" : "INACTIVE"})
                .toArray(Object[][]::new);
    }

    // Bottom navigation operations

    public static void viewUserInfo() {
        System.out.println("DatabaseCallbacks.viewUserInfo");
    }

    public static void sendPromotion() {
        System.out.println("DatabaseCallbacks.sendPromotion");
    }
}
