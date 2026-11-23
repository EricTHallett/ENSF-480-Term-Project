package ca.ucalgary.ensf480.flightreservation.domain;

public class User {
    private final int id;
    private UserRole role;
    private String email;
    private String firstName;
    private String lastName;
    private boolean isActive;

    public User(int id, UserRole role, String email, String firstName, String lastName, boolean isActive) {
        this.id = id;
        this.role = role;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isActive = isActive;
    }

    public int getId() {
        return id;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
}
