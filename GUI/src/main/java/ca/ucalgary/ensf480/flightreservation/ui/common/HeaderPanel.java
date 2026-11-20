package ca.ucalgary.ensf480.flightreservation.ui.common;

import ca.ucalgary.ensf480.flightreservation.session.UserSession;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

/** Persistent dashboard header showing the active session. */
public final class HeaderPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    public HeaderPanel(UserSession session) {
        super(new BorderLayout());
        setBackground(UiStyle.HEADER);
        setBorder(BorderFactory.createEmptyBorder(12, 16, 12, 16));

        JLabel applicationLabel = new JLabel("Flight Reservation System");
        applicationLabel.setForeground(Color.WHITE);
        applicationLabel.setFont(applicationLabel.getFont().deriveFont(Font.BOLD, 20f));

        JLabel sessionLabel = new JLabel(
                session.getActiveRole().getDisplayName() + ": " + session.getDisplayName(),
                SwingConstants.RIGHT);
        sessionLabel.setForeground(Color.WHITE);

        add(applicationLabel, BorderLayout.WEST);
        add(sessionLabel, BorderLayout.EAST);
    }
}
