package ca.ucalgary.ensf480.flightreservation.session;

import java.util.Objects;

public final class UserSession {

    private final int personId;
    private final String displayName;
    private final Role activeRole;

    public UserSession(int personId, String displayName, Role activeRole) {
        this.personId = personId;
        this.displayName = Objects.requireNonNull(displayName, "displayName");
        this.activeRole = Objects.requireNonNull(activeRole, "activeRole");
    }

    public int getPersonId() {
        return personId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Role getActiveRole() {
        return activeRole;
    }
}
