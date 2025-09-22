package com.example.demo.model;

import java.time.LocalDate;

public class Training {
    private Long trainingId;
    private Trainee trainee;
    private Trainer trainer;
    private String trainingName;
    private String trainingType;
    private LocalDate trainingDate;
    private int duration; // in minutes

    public Training() {}

    public Training(Long trainingId, Trainee trainee, Trainer trainer,
                    String trainingName, String trainingType,
                    LocalDate trainingDate, int duration) {
        this.trainingId = trainingId;
        this.trainee = trainee;
        this.trainer = trainer;
        this.trainingName = trainingName;
        this.trainingType = trainingType;
        this.trainingDate = trainingDate;
        this.duration = duration;
    }

    // getters & setters
    public Long getTrainingId() { return trainingId; }
    public void setTrainingId(Long trainingId) { this.trainingId = trainingId; }

    public Trainee getTrainee() { return trainee; }
    public void setTrainee(Trainee trainee) { this.trainee = trainee; }

    public Trainer getTrainer() { return trainer; }
    public void setTrainer(Trainer trainer) { this.trainer = trainer; }

    public String getTrainingName() { return trainingName; }
    public void setTrainingName(String trainingName) { this.trainingName = trainingName; }

    public String getTrainingType() { return trainingType; }
    public void setTrainingType(String trainingType) { this.trainingType = trainingType; }

    public LocalDate getTrainingDate() { return trainingDate; }
    public void setTrainingDate(LocalDate trainingDate) { this.trainingDate = trainingDate; }

    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }

    @Override
    public String toString() {
        return "Training{" +
                "id=" + trainingId +
                ", name='" + trainingName + '\'' +
                ", date=" + trainingDate +
                ", duration=" + duration +
                '}';
    }
}
