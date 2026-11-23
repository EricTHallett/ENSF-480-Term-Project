package ca.ucalgary.ensf480.flightreservation.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ca.ucalgary.ensf480.flightreservation.domain.User;
import ca.ucalgary.ensf480.flightreservation.domain.UserRole;

public class UserDAO implements DAO<User> {
    private Connection dbConnect;

    public UserDAO(Connection dbConnect) {
        this.dbConnect = dbConnect;
    }

    @Override
    public void add(User elem) {

    }

    @Override
    public void softDelete(User elem) {
        // TODO Auto-generated method stub

    }

    @Override
    public void update(User elem) {
        // TODO Auto-generated method stub

    }

    @Override
    public User createFromResultSet(ResultSet result) throws SQLException {
        String email = result.getString("email");
        UserRole role = UserRole.valueOf(result.getString("role"));
        String firstName = result.getString("first_name");
        String lastName = result.getString("last_name");
        boolean isActive = result.getInt("is_active") == 1 ? true : false;

        return new User(email, role, firstName, lastName, isActive);
    }

    public User findByEmailAndRole(String email, UserRole role) {
        User ret = null;

        try {
            PreparedStatement stmt = dbConnect.prepareStatement(
                "SELECT * FROM USER WHERE email=\"" + email + "\" AND role=\"" + role.name() + "\"");

            ResultSet results = stmt.executeQuery();

            while (results.next()) {
                ret = createFromResultSet(results);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

        return ret;
    }
}
