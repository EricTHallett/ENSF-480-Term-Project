package ca.ucalgary.ensf480.flightreservation.database;

import ca.ucalgary.ensf480.flightreservation.domain.User;
import ca.ucalgary.ensf480.flightreservation.domain.UserRole;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserDAO implements DAO<User> {
    private Connection dbConnect;

    public UserDAO(Connection dbConnect) {
        this.dbConnect = dbConnect;
    }

    @Override
    public void add(User user) {
        try {
            PreparedStatement stmt = dbConnect.prepareStatement(
                    "INSERT INTO User (email, role, first_name, last_name, is_active, promotion_sent_on) VALUES (?, ?, ?, ?, ?, ?)");

            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getRole().name());
            stmt.setString(3, user.getFirstName());
            stmt.setString(4, user.getLastName());
            stmt.setInt(5, user.isActive() ? 1 : 0);
            stmt.setString(6, Objects.toString(user.getPromotionSentOn(), null));

            stmt.executeUpdate();
            stmt.close();
        }
        catch (SQLException e) {
            throw new RuntimeException("Error adding user", e);
        } 
    }

    @Override
    public void update(User user) {
        try {
            PreparedStatement stmt = dbConnect.prepareStatement(
                    "UPDATE User SET role=?, first_name=?, last_name=?, is_active=?, promotion_sent_on=? WHERE email=?");

            stmt.setString(1, user.getRole().name());
            stmt.setString(2, user.getFirstName());
            stmt.setString(3, user.getLastName());
            stmt.setInt(4, user.isActive() ? 1 : 0);
            stmt.setString(5, Objects.toString(user.getPromotionSentOn(), null));
            stmt.setString(6, user.getEmail());

            stmt.executeUpdate();
            stmt.close();
        }
        catch (SQLException e) {
            throw new RuntimeException("Error updating user", e);
        }
    }

    @Override
    public User createFromResultSet(ResultSet result) throws SQLException {
        String email = result.getString("email");
        UserRole role = UserRole.valueOf(result.getString("role"));
        String firstName = result.getString("first_name");
        String lastName = result.getString("last_name");
        boolean isActive = result.getInt("is_active") == 1 ? true : false;
        String sentOn = result.getString("promotion_sent_on");
        LocalDate promotionSentOn = sentOn == null ? null : LocalDate.parse(sentOn);

        return new User(email, role, firstName, lastName, isActive, promotionSentOn);
    }

    public User findByEmailAndRole(String email, UserRole role) {
        User ret = null;

        try {
            PreparedStatement stmt = dbConnect.prepareStatement(
                "SELECT * FROM User WHERE email = ? AND role = ?");
            stmt.setString(1, email);
            stmt.setString(2, role.name());

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

    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (PreparedStatement stmt = dbConnect.prepareStatement("SELECT * FROM User ORDER BY email");
            ResultSet results = stmt.executeQuery()) {
                while (results.next()) users.add(createFromResultSet(results));
            }
            catch (SQLException e) {
                throw new RuntimeException("Error listing users", e);
            }
            return users;
    }
}
