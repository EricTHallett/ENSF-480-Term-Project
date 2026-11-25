package ca.ucalgary.ensf480.flightreservation.ui.Dialogs;

import java.awt.GridLayout;
import java.util.function.Consumer;
import javax.swing.*;

public final class AircraftDialog {
    public static void showDialog(Consumer<String[]> callback) {
        JTextField aircraftModel = new JTextField(20);
        JTextField seats = new JTextField(20);

        JPanel panel = new JPanel(new GridLayout(0, 2, 8, 8));
        panel.add(new JLabel("Aircraft Model"));
        panel.add(aircraftModel);
        panel.add(new JLabel("Seats"));
        panel.add(seats);

        while (true) {
            int result = JOptionPane.showConfirmDialog(null, panel, "Add Aircraft",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result != JOptionPane.OK_OPTION) return;

            String[] values = new String[]{aircraftModel.getText().trim(), seats.getText().trim()};

            try {
                callback.accept(values);
                return;
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(panel, e.getMessage(),
                        "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
