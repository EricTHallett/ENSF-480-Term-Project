package ca.ucalgary.ensf480.flightreservation.ui.Navigation;

import java.util.function.Consumer;
import ca.ucalgary.ensf480.flightreservation.ui.Modules.Module;
import ca.ucalgary.ensf480.flightreservation.ui.Modules.Flights.FlightsModule;
import ca.ucalgary.ensf480.flightreservation.ui.Modules.Reservations.ReservationsModule;
import ca.ucalgary.ensf480.flightreservation.ui.Modules.Customers.CustomersModule;

public final class AdminNavigation implements Navigation {
    private final Consumer<Module> openModule;
    private final Runnable logout;

    public AdminNavigation(Consumer<Module> openModule, Runnable logout) {
        this.openModule = openModule;
        this.logout = logout;
    }

    public Object[][] getButtons() {
        return new Object[][]{
            {"Flights", (Runnable) () -> openModule.accept(new FlightsModule())},
            {"User Info", (Runnable) () -> System.out.println("User Info")},
            {"Logout", logout},
        };
    }
}
