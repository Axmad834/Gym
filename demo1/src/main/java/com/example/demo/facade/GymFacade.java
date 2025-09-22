package com.example.demo.facade;


import com.example.demo.model.Trainee;
import com.example.demo.model.Trainer;
import com.example.demo.model.Training;
import com.example.demo.service.TraineeService;
import com.example.demo.service.TrainerService;
import com.example.demo.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GymFacade {
    private final TrainerService trainerService;
    private final TraineeService traineeService;
    private final TrainingService trainingService;

    @Autowired
    public GymFacade(TrainerService trainerService,
                     TraineeService traineeService,
                     TrainingService trainingService) {
        this.trainerService = trainerService;
        this.traineeService = traineeService;
        this.trainingService = trainingService;
    }

    public Trainer createTrainer(Trainer t) { return trainerService.create(t); }
    public Trainee createTrainee(Trainee t) { return traineeService.create(t); }
    public Training createTraining(Training t) { return trainingService.create(t); }

    public List<Trainer> listTrainers() { return trainerService.findAll(); }
    public List<Trainee> listTrainees() { return traineeService.findAll(); }
    public List<Training> listTrainings() { return trainingService.findAll(); }
}
