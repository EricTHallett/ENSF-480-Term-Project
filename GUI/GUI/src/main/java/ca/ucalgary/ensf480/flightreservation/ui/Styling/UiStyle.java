package ca.ucalgary.ensf480.flightreservation.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

/** The appearance of the original GUI, separate from module callbacks. */
public final class UiStyle {
    public static final Color HEADER = new Color(31, 78, 120);
    public static final Color SEARCH_BACKGROUND = new Color(218, 232, 252);
    public static final Color ACTION_BACKGROUND = new Color(225, 213, 231);
    public static final Color ACTION = new Color(150, 115, 166);
    public static final Color NAVIGATION_BACKGROUND = new Color(238, 238, 238);

    private UiStyle() {}

    public static void searchPanel(JPanel panel) {
        panel.setBackground(SEARCH_BACKGROUND);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(108, 142, 191)),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
    }

    public static void actionPanel(JPanel panel) {
        panel.setPreferredSize(new Dimension(220, 0));
        panel.setBackground(ACTION_BACKGROUND);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACTION),
                BorderFactory.createEmptyBorder(16, 16, 16, 16)));
    }

    public static void tablePanel(JPanel panel, String title) {
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createTitledBorder(title + " Results"));
    }

    public static void table(JTable table) {
        table.setFillsViewportHeight(true);
        table.setRowHeight(28);
        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        table.getTableHeader().setReorderingAllowed(false);
    }

    public static void navigationPanel(JPanel panel) {
        panel.setBackground(NAVIGATION_BACKGROUND);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                BorderFactory.createEmptyBorder(10, 12, 10, 12)));
    }

    public static void heading(JLabel label) {
        label.setFont(label.getFont().deriveFont(Font.BOLD, 14f));
    }

    public static void primaryButton(JButton button) {
        button.setBackground(HEADER);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
    }

    public static void secondaryButton(JButton button) {
        button.setBackground(Color.WHITE);
        button.setForeground(HEADER);
        button.setFocusPainted(false);
    }

    public static void actionButton(JButton button) {
        button.setBackground(Color.WHITE);
        button.setForeground(new Color(91, 60, 111));
        button.setFocusPainted(false);
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
    }

    public static void dangerButton(JButton button) {
        button.setBackground(new Color(248, 206, 204));
        button.setForeground(new Color(138, 28, 28));
        button.setFocusPainted(false);
    }
}
