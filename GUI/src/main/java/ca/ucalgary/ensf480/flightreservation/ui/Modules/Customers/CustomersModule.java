package ca.ucalgary.ensf480.flightreservation.ui.Modules.Customers;

import java.util.Arrays;
import java.util.function.Consumer;
import ca.ucalgary.ensf480.flightreservation.ui.Modules.Module;

public final class CustomersModule implements Module {
    private final Object[][] actionButtons = {
        {"Add Customer", (Consumer<String[]>) this::addCustomer},
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
    public void search(String[] values) {
        System.out.println("Customers Search: " + Arrays.toString(values));
    }
    public void addCustomer(String[] args) { System.out.println("Customers: Add Customer"); }
    public void viewCustomer(String[] args) { System.out.println("Customers: View Customer"); }
    public void deactivateCustomer(String[] args) { System.out.println("Customers: Deactivate Customer"); }
    public void reactivateCustomer(String[] args) { System.out.println("Customers: Reactivate Customer"); }
    public void deleteCustomer(String[] args) { System.out.println("Customers: Delete Customer"); }


    //Callbacks go here
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
