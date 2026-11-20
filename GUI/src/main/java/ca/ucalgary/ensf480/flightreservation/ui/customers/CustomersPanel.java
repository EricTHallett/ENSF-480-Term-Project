package ca.ucalgary.ensf480.flightreservation.ui.customers;
import ca.ucalgary.ensf480.flightreservation.session.Role;
import ca.ucalgary.ensf480.flightreservation.ui.common.ModulePanel;
import javax.swing.JFrame;

public final class CustomersPanel extends ModulePanel {

    private static final long serialVersionUID = 1L;

    public CustomersPanel(JFrame owner, Role role) {
        super(owner, "Customers",
                new String[]{"Name", "Email", "Phone"},
                new String[]{"Add Customer", "Edit Customer", "Deactivate Customer", "View Customer"});
                populateTable(columnsFor(role), new Object[][]{{},{}});
    }
    private static String[] columnsFor(Role role){
        switch(role){
            case CUSTOMER:
                return new String[]{"PLACE HOLDER 2056", "PLACE HOLDER 2057"};
            case FLIGHT_AGENT:
                return new String[]{"PLACE HOLDER 2056", "PLACE HOLDER 2057","PLACE HOLDER 2058","PLACE HOLDER 2059"};
            case ADMINISTRATOR:
                return new String[]{"PLACE HOLDER 2060", "PLACE HOLDER 2061","PLACE HOLDER 2062","PLACE HOLDER 2063"};
            case SUPER_USER:
                return new String[]{"PLACE HOLDER 2060", "PLACE HOLDER 2061","PLACE HOLDER 2062","PLACE HOLDER 2063"
                ,"PLACE HOLDER 2056", "PLACE HOLDER 2057","PLACE HOLDER 2058","PLACE HOLDER 2059"};
            default:
                throw new IllegalArgumentException("Unsupported role: " + role);
        }
    }
}
