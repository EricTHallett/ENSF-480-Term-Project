package ca.ucalgary.ensf480.flightreservation.ui.Modules.Reservations;

import java.util.Arrays;
import java.util.function.Consumer;
import ca.ucalgary.ensf480.flightreservation.ui.Modules.Module;

public final class ReservationsModule implements Module {
    private final Object[][] actionButtons = {
        {"View Reservation", (Consumer<String[]>) this::viewReservation},
        {"Cancel Reservation", (Consumer<String[]>) this::cancelReservation},
        {"Remove Reservation", (Consumer<String[]>) this::removeReservation},
    };
    public String[] getSearchFields() {
        return new String[]{"Reservation ID","Customer ID","Flight ID"};
    }
    public Consumer<String[]> getSearchCallback() { return this::search; }
    public Object[][] getActionButtonArray() { return actionButtons; }
    public void search(String[] values) {
        System.out.println("Reservations Search: " + Arrays.toString(values));
    }
    public void viewReservation(String[] args) { System.out.println("Reservations: View Reservation"); }
    public void cancelReservation(String[] args) { System.out.println("Reservations: Cancel Reservation"); }
    public void removeReservation(String[] args) { System.out.println("Reservations: Remove Reservation"); }




    //Data Base Callbacks go here
    public String[] getTableColumns() { return new String[]{"Reservation ID", "Customer ID", "Flight ID", "Status"}; }
    public Object[][] getTableData() {
        return new Object[][]{
            {1, 1, 1, "CONFIRMED"},
            {2, 2, 1, "CANCELLED"},
            {3, 1, 2, "CONFIRMED"}
        };
    }
    
}
