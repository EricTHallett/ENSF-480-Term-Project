package ca.ucalgary.ensf480.flightreservation;

import java.io.IOException;
import java.sql.SQLException;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import ca.ucalgary.ensf480.flightreservation.database.DatabaseInitializer;
import ca.ucalgary.ensf480.flightreservation.ui.MainFrame;

public final class App {

    private App() {
    }

    public static void main(String[] args) throws SQLException, IOException{
        DatabaseInitializer.main(args);
        SwingUtilities.invokeLater(() -> {
            useSystemDefaults();
            new MainFrame().setVisible(true);
        });
    }

    private static void useSystemDefaults() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException exception) {
        }
    }
}
