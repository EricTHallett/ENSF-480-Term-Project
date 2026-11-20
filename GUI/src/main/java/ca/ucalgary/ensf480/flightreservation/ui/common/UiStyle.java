package ca.ucalgary.ensf480.flightreservation.ui.common;

import javax.swing.JButton;
import java.awt.Color;

public final class UiStyle {

    public static final Color HEADER = new Color(31, 78, 120);
    public static final Color SEARCH_BACKGROUND = new Color(218, 232, 252);
    public static final Color ACTION_BACKGROUND = new Color(225, 213, 231);
    public static final Color ACTION = new Color(150, 115, 166);
    public static final Color NAVIGATION_BACKGROUND = new Color(238, 238, 238);

    private UiStyle() {
    }

    public static void primaryButton(JButton button) {
        button.setBackground(HEADER);
        button.setForeground(Color.RED);
        button.setFocusPainted(false);
    }

    public static void secondaryButton(JButton button) {
        button.setBackground(Color.WHITE);
        button.setForeground(HEADER);
        button.setFocusPainted(false);
    }

    public static void dangerButton(JButton button) {
        button.setBackground(new Color(248, 206, 204));
        button.setForeground(new Color(138, 28, 28));
        button.setFocusPainted(false);
    }
}
