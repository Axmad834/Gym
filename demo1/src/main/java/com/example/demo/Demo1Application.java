package com.example.demo;

import com.example.demo.facade.GymFacade;
import com.example.demo.model.Trainee;
import com.example.demo.model.Trainer;
import com.example.demo.model.Training;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;

@SpringBootApplication
public class Demo1Application {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Demo1Application.class, args);

        GymFacade facade = context.getBean(GymFacade.class);

        System.out.println("Initial trainers: " + facade.listTrainers());
        System.out.println("Initial trainees: " + facade.listTrainees());
        System.out.println("Initial trainings: " + facade.listTrainings());

        Trainer newTrainer = new Trainer(null, "John", "Smith", null, null, "Strength", 5);
        newTrainer = facade.createTrainer(newTrainer);
        System.out.println("Created trainer: " + newTrainer);

        Trainee newTrainee = new Trainee(null, "John", "Smith", null, null,
                LocalDate.of(1995, 1, 1), "123 Main St", true);
        newTrainee = facade.createTrainee(newTrainee);
        System.out.println("Created trainee: " + newTrainee);

        Training tr = new Training(null, newTrainee, newTrainer,
                "Morning Session", "One-to-one", LocalDate.now(), 60);
        tr = facade.createTraining(tr);
        System.out.println("Created training: " + tr);
    }
}

