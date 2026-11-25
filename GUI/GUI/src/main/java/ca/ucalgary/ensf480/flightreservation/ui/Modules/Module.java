package ca.ucalgary.ensf480.flightreservation.ui.Modules;

import java.util.function.Consumer;

public interface Module {
    String[] getSearchFields();
    Consumer<String[]> getSearchCallback();
    Object[][] getActionButtonArray(); // label, Consumer<String[]>, optional false for actions without row selection
    String[] getTableColumns();
    Object[][] getTableData();
}
