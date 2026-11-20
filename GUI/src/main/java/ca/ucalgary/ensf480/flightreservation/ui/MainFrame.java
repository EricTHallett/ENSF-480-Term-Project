package ca.ucalgary.ensf480.flightreservation.ui;

import ca.ucalgary.ensf480.flightreservation.session.Role;
import ca.ucalgary.ensf480.flightreservation.session.UserSession;
import ca.ucalgary.ensf480.flightreservation.ui.aircraft.AircraftPanel;
import ca.ucalgary.ensf480.flightreservation.ui.common.DefaultDialog;
import ca.ucalgary.ensf480.flightreservation.ui.common.HeaderPanel;
import ca.ucalgary.ensf480.flightreservation.ui.common.ModulePanel;
import ca.ucalgary.ensf480.flightreservation.ui.common.ModuleType;
import ca.ucalgary.ensf480.flightreservation.ui.common.NavigationPanel;
import ca.ucalgary.ensf480.flightreservation.ui.customers.CustomersPanel;
import ca.ucalgary.ensf480.flightreservation.ui.flights.FlightsPanel;
import ca.ucalgary.ensf480.flightreservation.ui.reservations.ReservationsPanel;
import ca.ucalgary.ensf480.flightreservation.ui.routes.RoutesPanel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.EnumMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JPanel;

public final class MainFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private static final String ROLE_SELECTION_CARD = "roleSelection";
    private static final String DASHBOARD_CARD = "dashboard";

    private final CardLayout rootLayout = new CardLayout();
    private final JPanel rootPanel = new JPanel(rootLayout);
    private final JPanel dashboardPanel = new JPanel(new BorderLayout(12, 12));
    private final CardLayout moduleLayout = new CardLayout();
    private final JPanel moduleContainer = new JPanel(moduleLayout);
    private final Map<ModuleType, ModulePanel> modulePanels = new EnumMap<>(ModuleType.class);

    private UserSession activeSession;
    private NavigationPanel navigationPanel;

    public MainFrame() {
        super("Flight Reservation System");
        configureFrame();
        buildRootLayout();
    }

    private void configureFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1_100, 700));
        setSize(1_280, 780);
        setLocationRelativeTo(null);
    }

    private void buildRootLayout() {
        rootPanel.add(new RoleSelectionPanel(this::startSession), ROLE_SELECTION_CARD);
        rootPanel.add(dashboardPanel, DASHBOARD_CARD);
        setContentPane(rootPanel);
        rootLayout.show(rootPanel, ROLE_SELECTION_CARD);
    }

    private void startSession(Role role) {
        /* Add in actual login handling here probably needs to pull and verify from the DB might need diffrent login architecture once we get into it */
        activeSession = new UserSession(0, "PLACE HOLDER#12501", role);
        buildDashboard();
        rootLayout.show(rootPanel, DASHBOARD_CARD);
    }

    private void buildDashboard() {
        dashboardPanel.removeAll();
        dashboardPanel.setBackground(Color.WHITE);
        moduleContainer.removeAll();
        modulePanels.clear();

        for (ModuleType moduleType : activeSession.getActiveRole().getAllowedModules()) {
            ModulePanel panel = createModulePanel(moduleType, activeSession.getActiveRole());
            modulePanels.put(moduleType, panel);
            moduleContainer.add(panel, moduleType.name());
        }

        navigationPanel = new NavigationPanel(
                activeSession.getActiveRole(),
                this::showModule,
                this::showProfile,
                this::showProfile,
                this::logout);

        JPanel dashboardContent = new JPanel(new BorderLayout(12, 12));
        dashboardContent.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 12, 0, 12));
        dashboardContent.setBackground(Color.WHITE);
        dashboardContent.add(moduleContainer, BorderLayout.CENTER);

        dashboardPanel.add(new HeaderPanel(activeSession), BorderLayout.NORTH);
        dashboardPanel.add(dashboardContent, BorderLayout.CENTER);
        dashboardPanel.add(navigationPanel, BorderLayout.SOUTH);

        showModule(ModuleType.FLIGHTS);
        dashboardPanel.revalidate();
        dashboardPanel.repaint();
    }

    private ModulePanel createModulePanel(ModuleType moduleType, Role role) {
        switch (moduleType) {
            case FLIGHTS:
                return new FlightsPanel(this, role);
            case RESERVATIONS:
                return new ReservationsPanel(this, role);
            case CUSTOMERS:
                return new CustomersPanel(this,role);
            case ROUTES:
                return new RoutesPanel(this,role);
            case AIRCRAFT:
                return new AircraftPanel(this,role);
            default:
                throw new IllegalArgumentException("Unsupported module: " + moduleType);
        }
    }

    private void showModule(ModuleType moduleType) {
        if (activeSession == null
                || !activeSession.getActiveRole().getAllowedModules().contains(moduleType)) {
            return;
        }
        moduleLayout.show(moduleContainer, moduleType.name());
        navigationPanel.setActiveModule(moduleType);
        //modulePanels.get(moduleType).populateTable();
    }

    private void showProfile() {
        //This needs the profile info handler 12503/1
        DefaultDialog.show(this, "My Profile");
    }
    private void showPromotion() {
        //This needs the profile info handler 12503/1
        DefaultDialog.show(this, "Promotion");
    }

    private void logout() {
        activeSession = null;
        modulePanels.clear();
        moduleContainer.removeAll();
        dashboardPanel.removeAll();
        rootLayout.show(rootPanel, ROLE_SELECTION_CARD);
    }
}
