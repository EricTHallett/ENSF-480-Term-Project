package ca.ucalgary.ensf480.flightreservation.session;

import ca.ucalgary.ensf480.flightreservation.ui.common.ModuleType;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

public enum Role {
    CUSTOMER("Customer", EnumSet.of(ModuleType.FLIGHTS, ModuleType.RESERVATIONS)),
    FLIGHT_AGENT("Flight Agent", EnumSet.of(ModuleType.FLIGHTS, ModuleType.RESERVATIONS, ModuleType.CUSTOMERS)),
    ADMINISTRATOR("Administrator", EnumSet.of(ModuleType.FLIGHTS, ModuleType.ROUTES, ModuleType.AIRCRAFT)),
    SUPER_USER("Super User",EnumSet.of(ModuleType.FLIGHTS, ModuleType.ROUTES, ModuleType.AIRCRAFT, ModuleType.RESERVATIONS, ModuleType.CUSTOMERS));
    private final String displayName;
    private final Set<ModuleType> allowedModules;

    Role(String displayName, Set<ModuleType> allowedModules) {
        this.displayName = displayName;
        this.allowedModules = Collections.unmodifiableSet(EnumSet.copyOf(allowedModules));
    }

    public String getDisplayName() {
        return displayName;
    }

    public Set<ModuleType> getAllowedModules() {
        return allowedModules;
    }
}
