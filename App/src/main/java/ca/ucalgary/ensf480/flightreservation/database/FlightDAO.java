package ca.ucalgary.ensf480.flightreservation.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ca.ucalgary.ensf480.flightreservation.domain.Flight;

public class FlightDAO implements DAO<Flight> {
    private Connection dbConnect;
    private static final String SELECT_FLIGHT =
            "SELECT f.id, a.name AS airline, r.origin, r.destination, f.departure_time, f.arrival_time, f.price_cents, "
            + "f.total_seats, f.available_seats, f.status FROM Flight f JOIN Airline a ON f.airline_code = a.airline_code "
            + "JOIN Route r ON f.route_id = r.id";

    public FlightDAO(Connection dbConnect) {
            this.dbConnect = dbConnect;
    }

    @Override
    public Flight createFromResultSet(ResultSet result) throws SQLException {
        int flightId = result.getInt("id");
        String airline = result.getString("airline");
        String origin = result.getString("origin");
        String destination = result.getString("destination");
        LocalDateTime departure = LocalDateTime.parse(result.getString("departure_time"));
        LocalDateTime arrival = LocalDateTime.parse(result.getString("arrival_time"));
        double price = result.getDouble("price_cents") / 100.0;
        int totalSeats = result.getInt("total_seats");
        int availableSeats = result.getInt("available_seats");
        Flight.FlightStatus status = Flight.FlightStatus.valueOf(result.getString("status"));

        return new Flight(flightId, airline, origin, destination, departure, arrival, price, totalSeats, availableSeats, status);
    }
}