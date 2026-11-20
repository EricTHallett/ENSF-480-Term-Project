package ca.ucalgary.ensf480.flightreservation.ui.common;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public final class DefaultDialog extends JDialog {

    private static final long serialVersionUID = 1L;

    public DefaultDialog(JFrame owner, String title) {
        super(owner, title, true);
        buildLayout();
        setSize(420, 220);
        setResizable(false);
        setLocationRelativeTo(owner);
    }

    private void buildLayout() {
        JPanel contentPanel = new JPanel(new BorderLayout(10, 16));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 16, 20));

        JLabel messageLabel = new JLabel("PLACE HOLDER#12503", SwingConstants.CENTER);
        JTextField inputField = new JTextField();

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        JButton confirmButton = new JButton("Confirm");
        JButton cancelButton = new JButton("Cancel");
        UiStyle.primaryButton(confirmButton);
        UiStyle.secondaryButton(cancelButton);
        //Add in CalL back handlers
        confirmButton.addActionListener(event -> dispose());
        cancelButton.addActionListener(event -> dispose());

        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);

        contentPanel.add(messageLabel, BorderLayout.NORTH);
        contentPanel.add(inputField, BorderLayout.CENTER);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);
        setContentPane(contentPanel);
    }

    public static void show(JFrame owner, String title) {
        new DefaultDialog(owner, title).setVisible(true);
    }
}
