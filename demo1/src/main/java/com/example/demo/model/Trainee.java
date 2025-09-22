package com.example.demo.model;

import java.time.LocalDate;

public class Trainee {
    private Long traineeId;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private LocalDate dateOfBirth;
    private String address;
    private boolean active;

    public Trainee() {}

    public Trainee(Long traineeId, String firstName, String lastName,
                   String username, String password,
                   LocalDate dateOfBirth, String address, boolean active) {
        this.traineeId = traineeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.active = active;
    }

    // getters & setters
    public Long getTraineeId() { return traineeId; }
    public void setTraineeId(Long traineeId) { this.traineeId = traineeId; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    @Override
    public String toString() {
        return "Trainee{" +
                "id=" + traineeId +
                ", name='" + firstName + " " + lastName + '\'' +
                ", active=" + active +
                '}';
    }
}
