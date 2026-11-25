package ca.ucalgary.ensf480.flightreservation.database;

import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private static DatabaseManager instance;

    private static final Path PATH = Path.of(System.getProperty("user.home"), "flightreservation.db").toAbsolutePath();
    private static final String URL = "jdbc:sqlite:" + PATH;
    private Connection connection;

    private DatabaseManager() {
        try { 
            connection = DriverManager.getConnection(URL); 
        }
        catch (SQLException e) {
            throw new RuntimeException("Error initializing database connection", e);
        }
    }

    public static DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }

        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public static Path getPath() {
        return PATH;
    }
}
