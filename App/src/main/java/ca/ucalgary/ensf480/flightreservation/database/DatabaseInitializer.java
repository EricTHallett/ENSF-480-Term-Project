package ca.ucalgary.ensf480.flightreservation.database;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {
    public static void main(String[] args) throws SQLException, IOException {
        Path path = DatabaseManager.getPath();
        if (Files.exists(path)) {
            Files.delete(path);
        }

        Connection db = DatabaseManager.getInstance().getConnection();
        Statement stmt = db.createStatement();

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("seed.sql");

        stmt.executeUpdate(new String(is.readAllBytes()));

        stmt.close();
    }
}
