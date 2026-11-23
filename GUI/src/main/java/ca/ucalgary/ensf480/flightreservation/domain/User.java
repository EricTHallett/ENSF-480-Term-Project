package ca.ucalgary.ensf480.flightreservation.domain;

import java.time.LocalDate;

public class User {
    private final String email;
    private UserRole role;
    private String firstName;
    private String lastName;
    private boolean isActive;
    private LocalDate promotionSentOn;

    public User(String email, UserRole role, String firstName, String lastName, boolean isActive, LocalDate promotionSentOn) {
        this.email = email;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isActive = isActive;
        this.promotionSentOn = promotionSentOn;
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

	public void setPromotionSentOn(LocalDate promotionSentOn) {
		this.promotionSentOn = promotionSentOn;
	}

    public LocalDate getPromotionSentOn() {
        return promotionSentOn;
    }
}
