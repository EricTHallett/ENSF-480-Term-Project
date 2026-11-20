package ca.ucalgary.ensf480.flightreservation.ui;

import ca.ucalgary.ensf480.flightreservation.session.Role;
import ca.ucalgary.ensf480.flightreservation.ui.common.UiStyle;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.function.Consumer;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public final class RoleSelectionPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    public RoleSelectionPanel(Consumer<Role> roleSelectionCallback) {
        super(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(80, 80, 80, 80));

        JLabel titleLabel = new JLabel("Flight Reservation System", SwingConstants.CENTER);
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 28f));
        add(titleLabel, BorderLayout.NORTH);

        JPanel rolePanel = new JPanel();
        rolePanel.setLayout(new BoxLayout(rolePanel, BoxLayout.Y_AXIS));
        rolePanel.setOpaque(false);

        JLabel instructionLabel = new JLabel("Select Role for Login", SwingConstants.CENTER);
        instructionLabel.setAlignmentX(CENTER_ALIGNMENT);
        instructionLabel.setFont(instructionLabel.getFont().deriveFont(16f));
        rolePanel.add(Box.createVerticalGlue());
        rolePanel.add(instructionLabel);
        rolePanel.add(Box.createVerticalStrut(28));

        for (Role role : Role.values()) {
            JButton roleButton = new JButton(role.getDisplayName());
            roleButton.setAlignmentX(CENTER_ALIGNMENT);
            roleButton.setMaximumSize(new Dimension(280, 48));
            UiStyle.primaryButton(roleButton);
            roleButton.addActionListener(event -> roleSelectionCallback.accept(role));
            rolePanel.add(roleButton);
            rolePanel.add(Box.createVerticalStrut(14));
        }
        rolePanel.add(Box.createVerticalGlue());
        add(rolePanel, BorderLayout.CENTER);

        JLabel loginStatusLabel = new JLabel(
                "Somthing Somthing Log in Status : PLACE HOLDER#12502", SwingConstants.CENTER);
        loginStatusLabel.setForeground(Color.DARK_GRAY);
        add(loginStatusLabel, BorderLayout.SOUTH);
    }
}
