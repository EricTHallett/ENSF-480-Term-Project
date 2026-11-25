package ca.ucalgary.ensf480.flightreservation.ui.Dialogs;

import java.awt.GridLayout;
import java.util.function.Consumer;
import javax.swing.*;

public final class FlightDialog {
    public static void showDialog(Consumer<String[]> callback) {
        JTextField airlineCode = new JTextField(20);
        JTextField routeId = new JTextField(20);
        JTextField aircraftId = new JTextField(20);
        JTextField departureTime = new JTextField(20);
        JTextField arrivalTime = new JTextField(20);
        JTextField totalSeats = new JTextField(20);
        JTextField availableSeats = new JTextField(20);
        JTextField priceCents = new JTextField(20);
        JTextField status = new JTextField(20);

        JPanel panel = new JPanel(new GridLayout(0, 2, 8, 8));
        panel.add(new JLabel("Airline Code"));
        panel.add(airlineCode);
        panel.add(new JLabel("Route ID"));
        panel.add(routeId);
        panel.add(new JLabel("Aircraft ID"));
        panel.add(aircraftId);
        panel.add(new JLabel("Departure Time"));
        panel.add(departureTime);
        panel.add(new JLabel("Arrival Time"));
        panel.add(arrivalTime);
        panel.add(new JLabel("Total Seats"));
        panel.add(totalSeats);
        panel.add(new JLabel("Available Seats"));
        panel.add(availableSeats);
        panel.add(new JLabel("Price (cents)"));
        panel.add(priceCents);
        panel.add(new JLabel("Status"));
        panel.add(status);

        while (true) {
            int result = JOptionPane.showConfirmDialog(null, panel, "Add Flight",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result != JOptionPane.OK_OPTION) return;

            String[] values = new String[]{airlineCode.getText().trim(), routeId.getText().trim(), aircraftId.getText().trim(), departureTime.getText().trim(), arrivalTime.getText().trim(), totalSeats.getText().trim(), availableSeats.getText().trim(), priceCents.getText().trim(), status.getText().trim()};

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
