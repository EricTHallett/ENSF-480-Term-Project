package ca.ucalgary.ensf480.flightreservation.ui.Navigation;

import java.util.function.Consumer;
import ca.ucalgary.ensf480.flightreservation.database.DatabaseCallbacks;
import ca.ucalgary.ensf480.flightreservation.ui.Modules.Module;
import ca.ucalgary.ensf480.flightreservation.ui.Modules.Flights.FlightsModule;
import ca.ucalgary.ensf480.flightreservation.ui.Modules.Reservations.ReservationsModule;
import ca.ucalgary.ensf480.flightreservation.ui.Modules.Customers.CustomersModule;
import ca.ucalgary.ensf480.flightreservation.ui.Modules.Aircraft.AircraftModule;

public final class FlightAgentNavigation implements Navigation {
    private final Consumer<Module> openModule;
    private final Runnable logout;

    public FlightAgentNavigation(Consumer<Module> openModule, Runnable logout) {
        this.openModule = openModule;
        this.logout = logout;
    }

    public Object[][] getButtons() {
        return new Object[][]{
            {"Flights", (Runnable) () -> openModule.accept(new FlightsModule())},
            {"Reservations", (Runnable) () -> openModule.accept(new ReservationsModule())},
            {"Customers", (Runnable) () -> openModule.accept(new CustomersModule())},
            {"Aircraft", (Runnable) () -> openModule.accept(new AircraftModule())},
            {"User Info", (Runnable) DatabaseCallbacks::viewUserInfo},
            {"Logout", logout},
        };
    }
}
