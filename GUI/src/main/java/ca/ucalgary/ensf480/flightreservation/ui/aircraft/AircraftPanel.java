package ca.ucalgary.ensf480.flightreservation.ui.aircraft;

import ca.ucalgary.ensf480.flightreservation.session.Role;
import ca.ucalgary.ensf480.flightreservation.ui.common.ModulePanel;
import javax.swing.JFrame;

public final class AircraftPanel extends ModulePanel {

    private static final long serialVersionUID = 1L;

    public AircraftPanel(JFrame owner, Role role) {
        super(owner, "Aircraft",
                new String[]{"Tail number", "Model", "Status"},
                new String[]{
                        "Add Aircraft", "Edit Aircraft", "Deactivate Aircraft",
                        "View Aircraft" });
                populateTable(columnsFor(role), new Object[][]{{},{}});

    }
    private static String[] columnsFor(Role role){
        switch(role){
            case CUSTOMER:
                return new String[]{"PLACE HOLDER 1056", "PLACE HOLDER 1057"};
            case FLIGHT_AGENT:
                return new String[]{"PLACE HOLDER 1056", "PLACE HOLDER 1057","PLACE HOLDER 1058","PLACE HOLDER 1059"};
            case ADMINISTRATOR:
                return new String[]{"PLACE HOLDER 1060", "PLACE HOLDER 1061","PLACE HOLDER 1062","PLACE HOLDER 1063"};
            case SUPER_USER:
                return new String[]{"PLACE HOLDER 1060", "PLACE HOLDER 1061","PLACE HOLDER 1062","PLACE HOLDER 1063"
                ,"PLACE HOLDER 2056", "PLACE HOLDER 1057","PLACE HOLDER 1058","PLACE HOLDER 1059"};
            default:
                throw new IllegalArgumentException("Unsupported role: " + role);
        }
    }
}
