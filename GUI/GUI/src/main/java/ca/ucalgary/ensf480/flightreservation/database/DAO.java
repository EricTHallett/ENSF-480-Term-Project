package ca.ucalgary.ensf480.flightreservation.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DAO<T> {
    void add(T elem);
    void update(T elem);
    T createFromResultSet(ResultSet result) throws SQLException;
}
