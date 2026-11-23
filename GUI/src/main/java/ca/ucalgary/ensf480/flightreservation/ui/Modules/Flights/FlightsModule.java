package ca.ucalgary.ensf480.flightreservation.ui.Modules.Flights;

import java.util.Arrays;
import java.util.function.Consumer;
import ca.ucalgary.ensf480.flightreservation.ui.Modules.Module;

public final class FlightsModule implements Module {
    private final Object[][] actionButtons = {
        {"Add Flight", (Consumer<String[]>) this::addFlight},
        {"Remove Flight", (Consumer<String[]>) this::removeFlight},
        {"Update Flight", (Consumer<String[]>) this::updateFlight},
        {"Cancle Flight", (Consumer<String[]>) this::cancleFlight}
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


    public void search(String[] text) { System.out.println("Search: " + Arrays.toString(text)); }
    public void addFlight(String[] text) { System.out.println("Add Flight"); }
    public void removeFlight(String[] text) { System.out.println("Remove Flight"); }
    public void updateFlight(String[] text) { System.out.println("Update Flight"); }
    public void cancleFlight(String[] text) { System.out.println("Cancle Flight"); }


    //Call backs here
    public String[] getTableColumns() {
        return new String[]{"Flight ID", "Origin", "Destination", "Status"};
    }
    public Object[][] getTableData() {
        return new Object[][]{
            {1, "Calgary", "Toronto", "ACTIVE"},
            {2, "Calgary", "Vancouver", "ACTIVE"},
            {3, "Edmonton", "Montreal", "CANCELLED"}
        };
    }
}
