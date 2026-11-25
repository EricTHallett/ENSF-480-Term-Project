package ca.ucalgary.ensf480.flightreservation;

import ca.ucalgary.ensf480.flightreservation.ui.MainFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public final class App {

    private App() {
    }

    public static void main(String[] args) {
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
