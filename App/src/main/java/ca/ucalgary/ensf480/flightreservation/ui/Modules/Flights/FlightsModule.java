package ca.ucalgary.ensf480.flightreservation.ui.Modules.Flights;

import java.util.function.Consumer;
import ca.ucalgary.ensf480.flightreservation.ui.Dialogs.FlightDialog;
import ca.ucalgary.ensf480.flightreservation.ui.Modules.Module;
import ca.ucalgary.ensf480.flightreservation.database.DatabaseCallbacks;

public final class FlightsModule implements Module {
    private final Object[][] actionButtons = {
        {"Add Flight", (Consumer<String[]>) this::addFlight, false},
        {"Remove Flight", (Consumer<String[]>) this::removeFlight},
        {"Update Flight", (Consumer<String[]>) this::updateFlight},
        {"Cancle Flight", (Consumer<String[]>) this::cancleFlight}
    };

    public String[] getSearchFields() {
        return new String[]{"Flight Id","Airline","Depature Time","Arrival Time","Total Seats","Available Seats","Price","Origin","Destination","Status"};
    }

    public Consumer<String[]> getSearchCallback() {
        return this::search;
    }

    public Object[][] getActionButtonArray() {
        return actionButtons;
    }


    public void search(String[] text) { DatabaseCallbacks.searchFlights(text); }
    public void addFlight(String[] args) {
        FlightDialog.showDialog(DatabaseCallbacks::addFlight,new FlightsModule());
    }
    public void removeFlight(String[] text) { DatabaseCallbacks.removeFlight(text); }
    public void updateFlight(String[] text) { DatabaseCallbacks.updateFlight(text); }
    public void cancleFlight(String[] text) { DatabaseCallbacks.cancelFlight(text); }
    public Object[][] searchDatabase(String[] value){
        return new Object[0][0];
    }


    //Call backs here
    public String[] getTableColumns() {
        return new String[]{"Flight Id","Airline","Depature Time","Arrival Time","Total Seats","Available Seats","Price","Origin","Destination","Status"};
    }
    public Object[][] getTableData() {
        return DatabaseCallbacks.listFlights();
    }
}
