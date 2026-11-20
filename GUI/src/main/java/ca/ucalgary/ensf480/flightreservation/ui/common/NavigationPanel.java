package ca.ucalgary.ensf480.flightreservation.ui.common;

import ca.ucalgary.ensf480.flightreservation.session.Role;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.Consumer;

@SuppressWarnings("serial")
public final class NavigationPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private final Map<ModuleType, JButton> moduleButtons = new EnumMap<>(ModuleType.class);

    public NavigationPanel(
            Role role,
            Consumer<ModuleType> moduleSelectionCallback,
            Runnable profileCallback,
            Runnable logoutCallback) {
        super(new BorderLayout());
        setBackground(UiStyle.NAVIGATION_BACKGROUND);
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(java.awt.Color.GRAY),
                BorderFactory.createEmptyBorder(10, 12, 10, 12)));

        JPanel modulePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        modulePanel.setOpaque(false);
        for (ModuleType moduleType : ModuleType.values()) {
            if (role.getAllowedModules().contains(moduleType)) {
                JButton button = new JButton(moduleType.getDisplayName());
                UiStyle.secondaryButton(button);
                button.addActionListener(event -> moduleSelectionCallback.accept(moduleType));
                moduleButtons.put(moduleType, button);
                modulePanel.add(button);
            }
        }

        JPanel sessionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        sessionPanel.setOpaque(false);
        JButton profileButton = new JButton("My Profile");
        JButton logoutButton = new JButton("Logout");
        UiStyle.secondaryButton(profileButton);
        UiStyle.dangerButton(logoutButton);
        profileButton.addActionListener(event -> profileCallback.run());
        logoutButton.addActionListener(event -> logoutCallback.run());
        sessionPanel.add(profileButton);
        sessionPanel.add(logoutButton);

        add(modulePanel, BorderLayout.WEST);
        add(sessionPanel, BorderLayout.EAST);
    }

    public void setActiveModule(ModuleType activeModule) {
        for (Map.Entry<ModuleType, JButton> entry : moduleButtons.entrySet()) {
            if (entry.getKey() == activeModule) {
                UiStyle.primaryButton(entry.getValue());
            } else {
                UiStyle.secondaryButton(entry.getValue());
            }
        }
    }
}
