package ca.ucalgary.ensf480.flightreservation.ui.Modules.Aircraft;

import java.util.function.Consumer;
import ca.ucalgary.ensf480.flightreservation.ui.Dialogs.AircraftDialog;
import ca.ucalgary.ensf480.flightreservation.ui.Modules.Module;
import ca.ucalgary.ensf480.flightreservation.database.DatabaseCallbacks;

public final class AircraftModule implements Module {
    private final Object[][] actionButtons = {
        {"Add Aircraft", (Consumer<String[]>) this::addAircraft, false},
        {"View Aircraft", (Consumer<String[]>) this::viewAircraft},
        {"Deactivate Aircraft", (Consumer<String[]>) this::deactivateAircraft},
        {"Reactivate Aircraft", (Consumer<String[]>) this::reactivateAircraft},
        {"Delete Aircraft", (Consumer<String[]>) this::deleteAircraft},
    };

    public String[] getSearchFields() {
        return new String[]{"Aircraft Id","Aircraft Model","Seats","Is Active"};
    }

    public Consumer<String[]> getSearchCallback() { return this::search; }

    public Object[][] getActionButtonArray() { return actionButtons; }



    //Module Interface
    public void search(String[] values) { DatabaseCallbacks.searchAircraft(values); }
    public void addAircraft(String[] args) {
        AircraftDialog.showDialog(DatabaseCallbacks::addAircraft, new AircraftModule());
    }
    public void viewAircraft(String[] args) { DatabaseCallbacks.viewAircraft(args); }
    public void deactivateAircraft(String[] args) { DatabaseCallbacks.deactivateAircraft(args); }
    public void reactivateAircraft(String[] args) { DatabaseCallbacks.reactivateAircraft(args); }
    public void deleteAircraft(String[] args) { DatabaseCallbacks.deleteAircraft(args); }


    //Callbacks here
    public String[] getTableColumns() { return new String[]{"Aircraft ID", "Aircraft Model", "Seats", "Is Active"}; }
    public Object[][] getTableData() {
        return DatabaseCallbacks.listAircraft();
    }
}
