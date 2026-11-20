package ca.ucalgary.ensf480.flightreservation.ui.routes;

import ca.ucalgary.ensf480.flightreservation.session.Role;
import ca.ucalgary.ensf480.flightreservation.ui.common.ModulePanel;
import javax.swing.JFrame;

public final class RoutesPanel extends ModulePanel {

    private static final long serialVersionUID = 1L;

    public RoutesPanel(JFrame owner, Role role) {
        super(owner, "Routes",
                new String[]{"Origin", "Destination", "Status"},
                new String[]{"Add Route", "Edit Route", "Deactivate Route", "View Route"});

                populateTable(columnsFor(role), new Object[][]{{},{}});
    }
    private static String[] columnsFor(Role role){
        switch(role){
            case CUSTOMER:
                return new String[]{"PLACE HOLDER 4056", "PLACE HOLDER 4057"};
            case FLIGHT_AGENT:
                return new String[]{"PLACE HOLDER 4056", "PLACE HOLDER 4057","PLACE HOLDER 4058","PLACE HOLDER 4059"};
            case ADMINISTRATOR:
                return new String[]{"PLACE HOLDER 4060", "PLACE HOLDER 4061","PLACE HOLDER 4062","PLACE HOLDER 4063"};
            case SUPER_USER:
                return new String[]{"PLACE HOLDER 4060", "PLACE HOLDER 4061","PLACE HOLDER 4062","PLACE HOLDER 4063"
                ,"PLACE HOLDER 2056", "PLACE HOLDER 4057","PLACE HOLDER 4058","PLACE HOLDER 4059"};
            default:
                throw new IllegalArgumentException("Unsupported role: " + role);
        }
    }
}
