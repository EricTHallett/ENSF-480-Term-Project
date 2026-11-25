package ca.ucalgary.ensf480.flightreservation.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ca.ucalgary.ensf480.flightreservation.domain.Reservation;
import ca.ucalgary.ensf480.flightreservation.domain.ReservationStatus;
import ca.ucalgary.ensf480.flightreservation.domain.User;
import ca.ucalgary.ensf480.flightreservation.domain.UserRole;

public class ReservationDAO implements DAO<Reservation> {
    private Connection dbConnect;
    private UserDAO userDao;
    private FlightDAO flightDao;

    public ReservationDAO(Connection dbConnect) {
        this.dbConnect = dbConnect;
        this.userDao = new UserDAO(dbConnect);
        this.flightDao = new FlightDAO(dbConnect);
    }

    @Override
    public void add(Reservation reservation) {
        flightDao.update(reservation.getFlight());

        try {
            PreparedStatement stmt = dbConnect.prepareStatement(
                "INSERT INTO Reservation (passenger_email, flight_id, num_seats, price_cents, status) VALUES (?, ?, ?, ?, ?)");

            stmt.setString(1, reservation.getPassenger().getEmail());
            stmt.setInt(2, reservation.getFlight().getId());
            stmt.setInt(3, reservation.getNumSeats());
            stmt.setInt(4, reservation.getPriceCents());
            stmt.setString(5, reservation.getStatus().name());

            stmt.executeUpdate();
            stmt.close();
        }
        catch (SQLException e) {
            throw new RuntimeException("Error adding reservation", e);
        }
    } 

    @Override
    public void update(Reservation reservation) {
        flightDao.update(reservation.getFlight());

        try {
            PreparedStatement stmt = dbConnect.prepareStatement(
                "UPDATE Reservation SET num_seats=?, price_cents=?, status=? WHERE passenger_email=? AND flight_id=?");
 
            stmt.setInt(1, reservation.getNumSeats());
            stmt.setInt(2, reservation.getPriceCents());
            stmt.setString(3, reservation.getStatus().name());
            stmt.setString(4, reservation.getPassenger().getEmail());
            stmt.setInt(5, reservation.getFlight().getId());

            stmt.executeUpdate();
            stmt.close();
        }
        catch (SQLException e) {
            throw new RuntimeException("Error updating reservation", e);
        }
    }

    @Override
    public Reservation createFromResultSet(ResultSet result) throws SQLException {
        User passenger = userDao.findByEmailAndRole(result.getString("passenger_email"), UserRole.CUSTOMER);
        Flight flight = flightDao.findById(result.getInt("flight_id"));
        int numSeats = result.getInt("num_seats");
        ReservationStatus status = ReservationStatus.valueOf(result.getString("status"));

        return new Reservation(passenger, flight, numSeats, status);
    }

    public Reservation findByPassengerAndFlight(String passengerEmail, int flightId) {
        Reservation ret = null;

        try {
            PreparedStatement stmt = dbConnect.prepareStatement(
                "SELECT * FROM Reservation WHERE passenger_email=\"" + passengerEmail + "\" AND flight_id=\"" + flightId + "\"");

            ResultSet results = stmt.executeQuery();

            while (results.next()) {
                ret = createFromResultSet(results);
            }

            results.close();
            stmt.close();
        }
        catch (SQLException e) {
            throw new RuntimeException("Error searching for user", e);
        }

        return ret;

    }
}
