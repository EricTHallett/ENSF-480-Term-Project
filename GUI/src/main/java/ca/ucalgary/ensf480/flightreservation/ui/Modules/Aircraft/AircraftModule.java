package ca.ucalgary.ensf480.flightreservation.ui.Modules.Aircraft;

import java.util.Arrays;
import java.util.function.Consumer;
import ca.ucalgary.ensf480.flightreservation.ui.Modules.Module;

public final class AircraftModule implements Module {
    private final Object[][] actionButtons = {
        {"Add Aircraft", (Consumer<String[]>) this::addAircraft},
        {"View Aircraft", (Consumer<String[]>) this::viewAircraft},
        {"Deactivate Aircraft", (Consumer<String[]>) this::deactivateAircraft},
        {"Reactivate Aircraft", (Consumer<String[]>) this::reactivateAircraft},
        {"Delete Aircraft", (Consumer<String[]>) this::deleteAircraft},
    };

    public String[] getSearchFields() {
        return new String[]{"Person ID","Status"};
    }

    public Consumer<String[]> getSearchCallback() { return this::search; }

    public Object[][] getActionButtonArray() { return actionButtons; }



    //Module Interface
    public void search(String[] values) {
        System.out.println("Customers Search: " + Arrays.toString(values));
    }
    public void addAircraft(String[] args) { System.out.println("Aircraft: Add Aircraft"); }
    public void viewAircraft(String[] args) { System.out.println("Aircraft: View Aircraft"); }
    public void deactivateAircraft(String[] args) { System.out.println("Aircraft: Deactivate Aircraft"); }
    public void reactivateAircraft(String[] args) { System.out.println("Aircraft: Reactivate Aircraft"); }
    public void deleteAircraft(String[] args) { System.out.println("Aircraft: Delete Aircraft"); }


    //Callbacks here
    public String[] getTableColumns() { return new String[]{"Person ID", "Role", "Status"}; }
    public Object[][] getTableData() {
        return new Object[][]{
            {1, "CUSTOMER", "ACTIVE"},
            {2, "CUSTOMER", "ACTIVE"},
            {3, "AGENT", "ACTIVE"},
            {4, "ADMIN", "ACTIVE"}
        };
    }
}
