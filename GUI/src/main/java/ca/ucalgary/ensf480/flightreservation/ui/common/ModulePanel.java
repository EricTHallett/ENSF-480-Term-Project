package ca.ucalgary.ensf480.flightreservation.ui.common;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings({"serial", "this-escape"})
public abstract class ModulePanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private final JFrame owner;
    private final String moduleName;
    private final List<JTextField> filterFields = new ArrayList<>();
    private final JTable resultsTable = new JTable();

    protected ModulePanel(JFrame owner,String moduleName,String[] filterLabels,String[] actionLabels) {
        super(new BorderLayout(12, 12));
        this.owner = owner;
        this.moduleName = moduleName;
        setBackground(Color.WHITE);
        setBorder(new EmptyBorder(0, 0, 0, 0));

        add(createSearchPanel(filterLabels), BorderLayout.NORTH);
        add(createActionPanel(actionLabels), BorderLayout.WEST);
        add(createResultsPanel(), BorderLayout.CENTER);
        //populateTable();//DUMMY DATA;
    }

    private JPanel createSearchPanel(String[] filterLabels) {
        JPanel outerPanel = new JPanel(new BorderLayout(0, 6));
        outerPanel.setBackground(UiStyle.SEARCH_BACKGROUND);
        outerPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(108, 142, 191)),
                new EmptyBorder(8, 10, 10, 10)));

        JLabel searchLabel = new JLabel("SEARCH " + moduleName.toUpperCase());
        searchLabel.setFont(searchLabel.getFont().deriveFont(Font.BOLD, 13f));
        outerPanel.add(searchLabel, BorderLayout.NORTH);

        JPanel fieldsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        fieldsPanel.setOpaque(false);
        for (String filterLabel : filterLabels) {
            JTextField field = new JTextField(12);
            filterFields.add(field);
            fieldsPanel.add(createFilterField(filterLabel, field));
        }

        JButton searchButton = new JButton("Search");
        JButton clearButton = new JButton("Clear");
        UiStyle.primaryButton(searchButton);
        UiStyle.secondaryButton(clearButton);
        searchButton.addActionListener(event -> onSearch());
        clearButton.addActionListener(event -> onClearSearch());
        fieldsPanel.add(searchButton);
        fieldsPanel.add(clearButton);

        outerPanel.add(fieldsPanel, BorderLayout.CENTER);
        return outerPanel;
    }

    private JPanel createFilterField(String labelText, JTextField field) {
        JPanel fieldPanel = new JPanel(new BorderLayout(0, 2));
        fieldPanel.setOpaque(false);
        fieldPanel.add(new JLabel(labelText), BorderLayout.NORTH);
        fieldPanel.add(field, BorderLayout.CENTER);
        return fieldPanel;
    }

    private JPanel createActionPanel(String[] actionLabels) {
        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new BoxLayout(actionPanel, BoxLayout.Y_AXIS));
        actionPanel.setPreferredSize(new Dimension(220, 0));
        actionPanel.setBackground(UiStyle.ACTION_BACKGROUND);
        actionPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(UiStyle.ACTION),
                new EmptyBorder(16, 16, 16, 16)));

        JLabel actionLabel = new JLabel("ACTIONS", SwingConstants.CENTER);
        actionLabel.setFont(actionLabel.getFont().deriveFont(Font.BOLD, 14f));
        actionLabel.setAlignmentX(CENTER_ALIGNMENT);
        actionPanel.add(actionLabel);
        actionPanel.add(Box.createVerticalStrut(20));

        for (String actionName : actionLabels) {
            JButton actionButton = new JButton(actionName);
            configureActionButton(actionButton);
            actionButton.addActionListener(event -> onAction(actionName));
            actionPanel.add(actionButton);
            actionPanel.add(Box.createVerticalStrut(10));
        }

        actionPanel.add(Box.createVerticalGlue());
        return actionPanel;
    }

    private void configureActionButton(JButton button) {
        button.setAlignmentX(CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        button.setBackground(Color.WHITE);
        button.setForeground(new Color(91, 60, 111));
        button.setFocusPainted(false);
    }

    private JPanel createResultsPanel() {
        JPanel resultsPanel = new JPanel(new BorderLayout());
        resultsPanel.setBackground(Color.WHITE);
        resultsPanel.setBorder(BorderFactory.createTitledBorder(moduleName + " Results"));
        resultsTable.setFillsViewportHeight(true);
        resultsTable.setRowHeight(28);
        resultsTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        resultsTable.getTableHeader().setReorderingAllowed(false);
        resultsPanel.add(new JScrollPane(resultsTable), BorderLayout.CENTER);
        return resultsPanel;
    }

    public final void populateTable(String[] header,Object [][] data) {
        DefaultTableModel defaultModel = new DefaultTableModel(
                data,
                header) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return true;
            }
        };
        resultsTable.setModel(defaultModel);
    }

    protected final JTable getResultsTable() {
        return resultsTable;
    }
    private void onSearch() {
        // TODO: Replace with the module's search/query callback.
        //populateTable();
    }

    private void onClearSearch() {
        for (JTextField filterField : filterFields) {
            filterField.setText("");
        }
        //populateTable();
    }

    private void onAction(String actionName) {
        //Replace With DB handler For each item 12503/2
        System.out.println(actionName);
        if (actionName == "View Flight Details" & 1 == 0){
            DefaultDialog.show(owner, actionName);
        }
        else{
            DefaultDialog.show(owner,"19044");//This is the place holder delet 
            //once all Actions are implementd this should be the fall through for ED/EC
        }
    }
}
