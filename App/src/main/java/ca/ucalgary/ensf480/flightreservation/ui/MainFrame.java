package ca.ucalgary.ensf480.flightreservation.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.Arrays;
import java.util.function.Consumer;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import ca.ucalgary.ensf480.flightreservation.ui.Modules.Module;
import ca.ucalgary.ensf480.flightreservation.ui.Modules.Flights.FlightsModule;
import ca.ucalgary.ensf480.flightreservation.ui.Navigation.AdminNavigation;
import ca.ucalgary.ensf480.flightreservation.ui.Navigation.FlightAgentNavigation;
import ca.ucalgary.ensf480.flightreservation.ui.Navigation.Navigation;
import ca.ucalgary.ensf480.flightreservation.ui.Navigation.SuperUserNavigation;
import ca.ucalgary.ensf480.flightreservation.ui.Navigation.UserNavigation;
import ca.ucalgary.ensf480.flightreservation.ui.Styling.UiStyle;

public final class MainFrame extends JFrame {
    private final Navigation navigation;

    public MainFrame() {
        super("Flight Reservation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 700);
        setLocationRelativeTo(null);

        String[] roles = {"User", "Flight Agent", "Admin", "Super User"};
        String role = (String) JOptionPane.showInputDialog(this, "Select role", "Role",
                JOptionPane.QUESTION_MESSAGE, null, roles, roles[0]);
        if (role == null) role = "User";

        navigation = switch (role) {
            case "Flight Agent" -> new FlightAgentNavigation(this::showModule, this::logout);
            case "Admin" -> new AdminNavigation(this::showModule, this::logout);
            case "Super User" -> new SuperUserNavigation(this::showModule, this::logout);
            default -> new UserNavigation(this::showModule, this::logout);
        };

        showModule(new FlightsModule());
    }

    private void showModule(Module module) {
        buildFrame(module, navigation);
        revalidate();
        repaint();
    }

    private void buildFrame(Module module, Navigation navigation) {
        //module = new CustomersModule();
        JPanel page = new JPanel(new BorderLayout(12, 12));
        page.setBackground(Color.WHITE);
        page.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        //JTable table = new JTable(new Object[0][0], module.getTableColumns());
        JTable table = new JTable(module.getTableData(), module.getTableColumns());
        UiStyle.table(table);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        page.add(createSearchBar(module,table), BorderLayout.NORTH);
        page.add(createActionBar(module, table), BorderLayout.WEST);
        page.add(createTableArea(module, table), BorderLayout.CENTER);
        page.add(createBottomBar(navigation), BorderLayout.SOUTH);
        setContentPane(page);
    }

    private JPanel createSearchBar(Module module, JTable table) {
        JPanel bar = new JPanel(new BorderLayout(0, 8));
        UiStyle.searchPanel(bar);
        JLabel heading = new JLabel("SEARCH " + module.getClass().getSimpleName().replace("Module", "").toUpperCase());
        UiStyle.heading(heading);
        bar.add(heading, BorderLayout.NORTH);

        JPanel fieldsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 0));
        fieldsPanel.setOpaque(false);
        String[] labels = module.getSearchFields();
        JTextField[] fields = new JTextField[labels.length];

        for (int i = 0; i < labels.length; i++) {
            JPanel fieldPanel = new JPanel(new BorderLayout(0, 2));
            fieldPanel.setOpaque(false);
            fieldPanel.add(new JLabel(labels[i]), BorderLayout.NORTH);
            fields[i] = new JTextField(8);
            fieldPanel.add(fields[i], BorderLayout.CENTER);
            fieldsPanel.add(fieldPanel);
        }

        JButton search = new JButton("Search");
        UiStyle.primaryButton(search);
        search.addActionListener(e -> {
            String[] values = Arrays.stream(fields)
                    .map(JTextField::getText)
                    .toArray(String[]::new);

            table.setModel(new DefaultTableModel(
                    module.searchDatabase(values),
                    module.getTableColumns()
            ));
        });

        fieldsPanel.add(search);

        JButton clear = new JButton("Clear");
        UiStyle.secondaryButton(clear);
        clear.addActionListener(e -> {
            for (JTextField field : fields) field.setText("");
        });
        fieldsPanel.add(clear);
        
        bar.add(fieldsPanel, BorderLayout.CENTER);
        return bar;
    }

    private JPanel createActionBar(Module module, JTable table) {
        JPanel bar = new JPanel();
        bar.setLayout(new BoxLayout(bar, BoxLayout.Y_AXIS));
        UiStyle.actionPanel(bar);

        JLabel heading = new JLabel("ACTIONS", SwingConstants.CENTER);
        UiStyle.heading(heading);
        heading.setAlignmentX(CENTER_ALIGNMENT);
        bar.add(heading);
        bar.add(Box.createVerticalStrut(20));

        for (Object[] entry : module.getActionButtonArray()) {
            String label = (String) entry[0];
            Consumer<String[]> callback = (Consumer<String[]>) entry[1];
            JButton button = new JButton(label);
            UiStyle.actionButton(button);
            button.setAlignmentX(CENTER_ALIGNMENT);
            
            button.addActionListener(e -> {
                int selectedRow = table.getSelectedRow();
                boolean requiresSelection = entry.length < 3 || (Boolean) entry[2];
                if (selectedRow < 0 && requiresSelection) {
                    JOptionPane.showMessageDialog(this, "Select a table row first.");
                    return;
                }

                String[] values = new String[table.getColumnCount()];
                if (requiresSelection) {
                    int modelRow = table.convertRowIndexToModel(selectedRow);
                    for (int i = 0; i < values.length; i++) {
                        Object value = table.getModel().getValueAt(modelRow, i);
                        values[i] = value == null ? "" : value.toString();
                    }
                } else {
                    values = new String[0];
                }
                callback.accept(values);
            });

            bar.add(button);
            bar.add(Box.createVerticalStrut(10));
        }
        bar.add(Box.createVerticalGlue());
        return bar;
    }

    private JPanel createTableArea(Module module, JTable table) {
        String title = module.getClass().getSimpleName().replace("Module", "");
        JPanel panel = new JPanel(new BorderLayout());
        UiStyle.tablePanel(panel, title);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createBottomBar(Navigation navigation) {
        JPanel bar = new JPanel(new BorderLayout());

        JPanel barLeft = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        JPanel barRight = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));

        UiStyle.navigationPanel(bar);

        for (Object[] entry : navigation.getButtons()) {
            String label = (String) entry[0];
            JButton button = new JButton(label);

            if ("Logout".equals(label)) {
                UiStyle.dangerButton(button);
            } else {
                UiStyle.secondaryButton(button);
            }


            Runnable callback = (Runnable) entry[1];
            button.addActionListener(e -> callback.run());

            if ("Logout".equals(label) || "User Info".equals(label)) {
                barRight.add(button);
            } else {
                barLeft.add(button);
            }
        }

        bar.add(barLeft, BorderLayout.WEST);
        bar.add(barRight, BorderLayout.EAST);

        return bar;
    }
    public void logout() {
        dispose();
        new MainFrame().setVisible(true);
    }
}
