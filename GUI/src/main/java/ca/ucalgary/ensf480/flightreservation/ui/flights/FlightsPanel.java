package ca.ucalgary.ensf480.flightreservation.ui.flights;

import ca.ucalgary.ensf480.flightreservation.session.Role;
import ca.ucalgary.ensf480.flightreservation.ui.common.ModulePanel;
import javax.swing.JFrame;

public final class FlightsPanel extends ModulePanel {

    private static final long serialVersionUID = 1L;

    public FlightsPanel(JFrame owner, Role role) {
        super(owner, "Flights",
                new String[]{"Origin", "Destination", "Airline", "Departure date", "Time of day"},
                actionsFor(role));
                populateTable(columnsFor(role), new Object[][]{{},{}});
    }

    private static String[] actionsFor(Role role) {
        switch (role) {
            case CUSTOMER:
                return new String[]{"Book Flight", "View Flight Details"};
            case FLIGHT_AGENT:
                return new String[]{"View Flight Details"};
            case ADMINISTRATOR:
                return new String[]{"Add Flight", "Edit Flight", "Cancel Flight", "View Flight Details"};
            case SUPER_USER:
                return new String[]{"Add Flight", "Edit Flight", "Cancel Flight", "View Flight Details"};
            default:
                throw new IllegalArgumentException("Unsupported role: " + role);
        }
    }
    private static String[] columnsFor(Role role){
        switch(role){
            case CUSTOMER:
                return new String[]{"PLACE HOLDER 056", "PLACE HOLDER 057"};
            case FLIGHT_AGENT:
                return new String[]{"PLACE HOLDER 056", "PLACE HOLDER 057","PLACE HOLDER 058","PLACE HOLDER 059"};
            case ADMINISTRATOR:
                return new String[]{"PLACE HOLDER 060", "PLACE HOLDER 061","PLACE HOLDER 062","PLACE HOLDER 063"};
            case SUPER_USER:
                return new String[]{"PLACE HOLDER 060", "PLACE HOLDER 061","PLACE HOLDER 062","PLACE HOLDER 063"
                ,"PLACE HOLDER 2056", "PLACE HOLDER 057","PLACE HOLDER 058","PLACE HOLDER 059"};
            default:
                throw new IllegalArgumentException("Unsupported role: " + role);
        }
    }
}
