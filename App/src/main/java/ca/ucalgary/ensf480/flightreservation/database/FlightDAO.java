package ca.ucalgary.ensf480.flightreservation.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

import ca.ucalgary.ensf480.flightreservation.domain.Flight;

public class FlightDAO implements DAO<Flight> {
    private Connection dbConnect;
    private static final String SELECT_FLIGHT =
            "SELECT f.id, a.name AS airline, f.origin, f.destination, f.departure_time, f.arrival_time, f.price_cents, " +
                    "f.total_seats, f.available_seats, f.status FROM Flight f";

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

    @Override
    public void add(Flight flight) {
        try {
            PreparedStatement stmt = dbConnect.prepareStatement(
                    "INSERT INTO Flight (airline_code, origin, destination, departure_time, arrival_time, total_seats, available_seats," +
                            " price_cents, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, flight.getAirline());
            stmt.setString(2, flight.getOrigin());
            stmt.setString(3, flight.getDestination());
            stmt.setString(4, flight.getDepartureTime().toString());
            stmt.setString(5, flight.getArrivalTime().toString());
            stmt.setInt(6, flight.getTotalSeats());
            stmt.setInt(7, flight.getAvailableSeats());
            stmt.setLong(8, Math.round(flight.getPrice() * 100));
            stmt.setString(9, flight.getStatus().name());

            stmt.executeUpdate();

            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                flight.assignId(keys.getInt(1));
            }
            keys.close();
            stmt.close();
        }
        catch (SQLException e) {
            throw new RuntimeException("Error adding flight", e);
        }
    }

    @Override
    public void update(Flight flight) {
        try {
            PreparedStatement stmt = dbConnect.prepareStatement(
                    "UPDATE Flight SET airline_code=?, origin=?, destination=?, departure_time=?, arrival_time=?, available_seats=?, " +
                            "price_cents=?, status=? WHERE id=?");
            stmt.setString(1, flight.getAirline());
            stmt.setString(2, flight.getOrigin());
            stmt.setString(3, flight.getDestination());
            stmt.setString(4, flight.getDepartureTime().toString());
            stmt.setString(5, flight.getArrivalTime().toString());
            stmt.setInt(6, flight.getAvailableSeats());
            stmt.setLong(7, Math.round(flight.getPrice() * 100));
            stmt.setString(8, flight.getStatus().name());
            stmt.setInt(9, flight.getFlightId());

            stmt.executeUpdate();
            stmt.close();
        }
        catch (SQLException e) {
            throw new RuntimeException("Error updating flight " + flight.getFlightId(), e);
        }
    }

    public Flight findById(int id) {
        Flight ret = null;

        try {
            PreparedStatement stmt = dbConnect.prepareStatement(SELECT_FLIGHT + " WHERE f.id = ?");
            stmt.setInt(1, id);

            ResultSet results = stmt.executeQuery();
            if (results.next()) {
                ret = createFromResultSet(results);
            }

            results.close();
            stmt.close();
        }
        catch (SQLException e) {
            throw new RuntimeException("Error finding flight " + id, e);
        }
        return ret;
    }

    public List<Flight> findAll() {
        List<Flight> flights = new ArrayList<>();

        try {
            PreparedStatement stmt = dbConnect.prepareStatement(SELECT_FLIGHT + " ORDER BY f.departure_time");
            ResultSet results = stmt.executeQuery();

            while (results.next()) {
                flights.add(createFromResultSet(results));
            }

            results.close();
            stmt.close();
        }
        catch (SQLException e) {
            throw new RuntimeException("Error finding flights", e);
        }
        return flights;
    }
}
