package ca.ucalgary.ensf480.flightreservation.ui.Modules.Customers;

import java.util.function.Consumer;
import ca.ucalgary.ensf480.flightreservation.ui.Dialogs.CustomerDialog;
import ca.ucalgary.ensf480.flightreservation.ui.Modules.Module;
import ca.ucalgary.ensf480.flightreservation.database.DatabaseCallbacks;

public final class CustomersModule implements Module {
    private final Object[][] actionButtons = {
        {"Add Customer", (Consumer<String[]>) this::addCustomer, false},
        {"View Customer", (Consumer<String[]>) this::viewCustomer},
        {"Deactivate Customer", (Consumer<String[]>) this::deactivateCustomer},
        {"Reactivate Customer", (Consumer<String[]>) this::reactivateCustomer},
        {"Delete Customer", (Consumer<String[]>) this::deleteCustomer},
    };

    public String[] getSearchFields() {
        return new String[]{"Person ID","Status"};
    }

    public Consumer<String[]> getSearchCallback() { return this::search; }

    public Object[][] getActionButtonArray() { return actionButtons; }



    //Module Interface
    public void search(String[] values) { DatabaseCallbacks.searchCustomers(values); }
    public void addCustomer(String[] args) {
        CustomerDialog.showDialog(DatabaseCallbacks::addCustomer);
    }
    public void viewCustomer(String[] args) { DatabaseCallbacks.viewCustomer(args); }
    public void deactivateCustomer(String[] args) { DatabaseCallbacks.deactivateCustomer(args); }
    public void reactivateCustomer(String[] args) { DatabaseCallbacks.reactivateCustomer(args); }
    public void deleteCustomer(String[] args) { DatabaseCallbacks.deleteCustomer(args); }


    //Callbacks go here
    public String[] getTableColumns() { return new String[]{"Person ID", "Role", "Status"}; }
    public Object[][] getTableData() {
        return DatabaseCallbacks.listCustomers();
    }
}
