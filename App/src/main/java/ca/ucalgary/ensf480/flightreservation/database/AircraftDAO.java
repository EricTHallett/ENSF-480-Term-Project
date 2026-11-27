package ca.ucalgary.ensf480.flightreservation.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ca.ucalgary.ensf480.flightreservation.domain.Aircraft;

public class AircraftDAO implements DAO<Aircraft> {
    private final Connection dbConnect;

    public AircraftDAO(Connection dbConnect) {
        this.dbConnect = dbConnect;
    }

    @Override
    public Aircraft createFromResultSet(ResultSet result) throws SQLException {
        return new Aircraft(result.getInt("id"), result.getString("aircraft_model"),
                result.getInt("seats"), result.getInt("is_active") == 1);
    }

    @Override
    public void add(Aircraft aircraft) {
        try (PreparedStatement stmt = dbConnect.prepareStatement(
            "INSERT INTO AIRCRAFT (aircraft_model, seats, is_active) VALUES (?, ?, ?)",
            Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, aircraft.getModel());
                stmt.setInt(2, aircraft.getSeats());
                stmt.setInt(3, aircraft.isActive() ? 1 : 0);
                stmt.executeUpdate();

                try (ResultSet keys = stmt.getGeneratedKeys()) {
                    if (keys.next()) aircraft.assignId(keys.getInt(1));
                }
            }
        catch (SQLException e) {
            throw new RuntimeException("Error adding aircraft", e);
        }
        
    }
    
    @Override
    public void update(Aircraft aircraft) {
        try (PreparedStatement stmt = dbConnect.prepareStatement(
            "UPDATE Aircraft SET aircraft_model = ?, seats = ?, is_active = ? WHERE id = ?")) {
                stmt.setString(1, aircraft.getModel());
                stmt.setInt(2, aircraft.getSeats());
                stmt.setInt(3, aircraft.isActive() ? 1 : 0);
                stmt.setInt(4, aircraft.getId());
                stmt.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException("Error updating aircraft " + aircraft.getId(), e);
        }
    }

    public List<Aircraft> findAll() {
        List<Aircraft> fleet = new ArrayList<>();
        try (PreparedStatement stmt = dbConnect.prepareStatement("SELECT * FROM Aircraft ORDER BY id");
            ResultSet results = stmt.executeQuery()) {
                while (results.next()) fleet.add(createFromResultSet(results));
            }
            catch (SQLException e) {
                throw new RuntimeException("Error listing aircraft", e);
            }
            return fleet;
    }
    
}
