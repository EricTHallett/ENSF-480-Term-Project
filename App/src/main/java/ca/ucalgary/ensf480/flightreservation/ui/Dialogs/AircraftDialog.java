package ca.ucalgary.ensf480.flightreservation.ui.Dialogs;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.function.Consumer;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import ca.ucalgary.ensf480.flightreservation.ui.Modules.Module;

public final class AircraftDialog {
    public static void showDialog(Consumer<String[]> callback, Module module) {
        ArrayList<JTextField> response = new ArrayList<>();
        JPanel panel = new JPanel(new GridLayout(0, 2, 8, 8));

        for (String str : module.getTableColumns()) {
            JTextField field = new JTextField(20);

            response.add(field);
            panel.add(new JLabel(str));
            panel.add(field);
        }

        while (true) {
            int result = JOptionPane.showConfirmDialog(null, panel, "Add Customer",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result != JOptionPane.OK_OPTION) return;

            String[] values = response.stream()
                    .map(JTextField::getText)
                    .toArray(String[]::new);

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
