package ca.ucalgary.ensf480.flightreservation.ui.Modules.Flights;

import java.util.function.Consumer;

import ca.ucalgary.ensf480.flightreservation.database.DatabaseCallbacks;
import ca.ucalgary.ensf480.flightreservation.ui.Dialogs.FlightDialog;
import ca.ucalgary.ensf480.flightreservation.ui.Modules.Module;

public final class FlightsModule implements Module {
    private final Object[][] actionButtons = {
        {"Add Flight", (Consumer<String[]>) this::addFlight, false},
        {"Remove Flight", (Consumer<String[]>) this::removeFlight},
        {"Update Flight", (Consumer<String[]>) this::updateFlight},
        {"Cancel Flight", (Consumer<String[]>) this::cancelFlight}
    };

    public String[] getSearchFields() {
        return new String[]{"Flight ID", "Origin", "Destination"};
    }

    public Consumer<String[]> getSearchCallback() {
        return this::search;
    }

    public Object[][] getActionButtonArray() {
        return actionButtons;
    }


    public void search(String[] text) { DatabaseCallbacks.searchFlights(text); }
    public void addFlight(String[] args) {
        FlightDialog.showDialog(DatabaseCallbacks::addFlight);
    }
    public void removeFlight(String[] text) { DatabaseCallbacks.removeFlight(text); }
    public void updateFlight(String[] text) { DatabaseCallbacks.updateFlight(text); }
    public void cancelFlight(String[] text) { DatabaseCallbacks.cancelFlight(text); }


    //Call backs here
    public String[] getTableColumns() {
        return new String[]{"Flight ID", "Origin", "Destination", "Status"};
    }
    public Object[][] getTableData() {
        return DatabaseCallbacks.listFlights();
    }
}
