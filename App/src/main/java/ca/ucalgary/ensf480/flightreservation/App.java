package ca.ucalgary.ensf480.flightreservation;

import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import ca.ucalgary.ensf480.flightreservation.ui.MainFrame;

public final class App {

    private App() {
    }

    public static void main(String[] args) throws SQLException, IOException {
        SwingUtilities.invokeLater(() -> {
            useSystemDefaults();
            try {
                new MainFrame().setVisible(true);
            } catch (RuntimeException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, e.getMessage(), "Startup Error", JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }
        });
    }

    private static void useSystemDefaults() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException exception) {
        }
    }
}
