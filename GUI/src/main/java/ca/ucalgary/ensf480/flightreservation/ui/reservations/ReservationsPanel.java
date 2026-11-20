package ca.ucalgary.ensf480.flightreservation.ui.reservations;

import ca.ucalgary.ensf480.flightreservation.session.Role;
import ca.ucalgary.ensf480.flightreservation.ui.common.ModulePanel;
import javax.swing.JFrame;

public final class ReservationsPanel extends ModulePanel {

    private static final long serialVersionUID = 1L;

    public ReservationsPanel(JFrame owner, Role role) {
        super(owner, "Reservations",
                new String[]{"Confirmation", "Status", "Departure date"},
                actionsFor(role));
                populateTable(columnsFor(role), new Object[][]{{},{}});
    }

    private static String[] actionsFor(Role role) {
        if (role == Role.CUSTOMER) {
            return new String[]{"View Reservation", "Change Flight or Seat", "Cancel Reservation"};
        }
        return new String[]{
                "Create Reservation", "View Reservation", "Change Flight or Seat", "Cancel Reservation"};
    }
    private static String[] columnsFor(Role role){
        switch(role){
            case CUSTOMER:
                return new String[]{"PLACE HOLDER 3056", "PLACE HOLDER 3057"};
            case FLIGHT_AGENT:
                return new String[]{"PLACE HOLDER 3056", "PLACE HOLDER 3057","PLACE HOLDER 3058","PLACE HOLDER 3059"};
            case ADMINISTRATOR:
                return new String[]{"PLACE HOLDER 3060", "PLACE HOLDER 3061","PLACE HOLDER 3062","PLACE HOLDER 3063"};
            case SUPER_USER:
                return new String[]{"PLACE HOLDER 3060", "PLACE HOLDER 3061","PLACE HOLDER 3062","PLACE HOLDER 3063"
                ,"PLACE HOLDER 2056", "PLACE HOLDER 3057","PLACE HOLDER 3058","PLACE HOLDER 3059"};
            default:
                throw new IllegalArgumentException("Unsupported role: " + role);
        }
    }
}
