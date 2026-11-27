package ca.ucalgary.ensf480.flightreservation.ui.Modules.Reservations;

import java.util.function.Consumer;

import ca.ucalgary.ensf480.flightreservation.database.DatabaseCallbacks;
import ca.ucalgary.ensf480.flightreservation.ui.Modules.Module;

public final class ReservationsModule implements Module {
    private final Object[][] actionButtons = {
        {"View Reservation", (Consumer<String[]>) this::viewReservation},
        {"Cancel Reservation", (Consumer<String[]>) this::cancelReservation},
        {"Remove Reservation", (Consumer<String[]>) this::removeReservation},
    };
    public String[] getSearchFields() {
        return new String[]{"Email", "Flight Id", "Booked Seats", "Price", "Status","Payment"};
    }
    public Object[][] getActionButtonArray() { return actionButtons; }
    public void viewReservation(String[] args) { DatabaseCallbacks.viewReservation(args); }
    public void cancelReservation(String[] args) { DatabaseCallbacks.cancelReservation(args); }
    public void removeReservation(String[] args) { DatabaseCallbacks.removeReservation(args); }
    public Object[][] searchDatabase(String[] value){
        return DatabaseCallbacks.searchReservation(value);
    }



    //Data Base Callbacks go here
    public String[] getTableColumns() { return new String[]{"Email", "Flight Id", "Booked Seats", "Price", "Status","Payment"}; }
    public Object[][] getTableData() {
        return DatabaseCallbacks.listReservations();
    }
    
}

