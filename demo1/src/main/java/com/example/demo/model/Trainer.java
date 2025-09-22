package com.example.demo.model;

public class Trainer {
    private Long trainerId;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String specialization;
    private int yearsOfExperience;

    public Trainer() {}

    public Trainer(Long trainerId, String firstName, String lastName,
                   String username, String password,
                   String specialization, int yearsOfExperience) {
        this.trainerId = trainerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.specialization = specialization;
        this.yearsOfExperience = yearsOfExperience;
    }

    // getters & setters
    public Long getTrainerId() { return trainerId; }
    public void setTrainerId(Long trainerId) { this.trainerId = trainerId; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public int getYearsOfExperience() { return yearsOfExperience; }
    public void setYearsOfExperience(int yearsOfExperience) { this.yearsOfExperience = yearsOfExperience; }

    @Override
    public String toString() {
        return "Trainer{" +
                "id=" + trainerId +
                ", name='" + firstName + " " + lastName + '\'' +
                ", specialization='" + specialization + '\'' +
                ", years=" + yearsOfExperience +
                '}';
    }
}
