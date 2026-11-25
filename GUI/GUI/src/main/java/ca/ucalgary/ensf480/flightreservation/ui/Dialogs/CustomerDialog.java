package ca.ucalgary.ensf480.flightreservation.ui.Dialogs;

import java.awt.GridLayout;
import java.util.function.Consumer;
import javax.swing.*;

public final class CustomerDialog {
    public static void showDialog(Consumer<String[]> callback) {
        JTextField email = new JTextField(20);
        JTextField firstName = new JTextField(20);
        JTextField lastName = new JTextField(20);

        JPanel panel = new JPanel(new GridLayout(0, 2, 8, 8));
        panel.add(new JLabel("Email"));
        panel.add(email);
        panel.add(new JLabel("First Name"));
        panel.add(firstName);
        panel.add(new JLabel("Last Name"));
        panel.add(lastName);

        while (true) {
            int result = JOptionPane.showConfirmDialog(null, panel, "Add Customer",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result != JOptionPane.OK_OPTION) return;

            String[] values = new String[]{email.getText().trim(), firstName.getText().trim(), lastName.getText().trim()};

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
