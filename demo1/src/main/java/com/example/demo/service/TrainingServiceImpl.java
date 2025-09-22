package com.example.demo.service;


import com.example.demo.dao.TraineeDao;
import com.example.demo.dao.TrainerDao;
import com.example.demo.dao.TrainingDao;
import com.example.demo.model.Training;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainingServiceImpl implements TrainingService {
    private static final Logger log = LoggerFactory.getLogger(TrainingServiceImpl.class);

    private TrainingDao trainingDao;
    private TrainerDao trainerDao;
    private TraineeDao traineeDao;

    @Autowired
    public void setTrainingDao(TrainingDao trainingDao) { this.trainingDao = trainingDao; }

    @Autowired
    public void setTrainerDao(TrainerDao trainerDao) { this.trainerDao = trainerDao; }

    @Autowired
    public void setTraineeDao(TraineeDao traineeDao) { this.traineeDao = traineeDao; }

    @Override
    public Training create(Training training) {
        // Optionally validate that referenced trainer & trainee exist
        if (training.getTrainer() == null || training.getTrainee() == null) {
            log.warn("Training must have both trainer and trainee set");
            throw new IllegalArgumentException("Trainer and Trainee must be set");
        }
        Training saved = trainingDao.save(training);
        log.info("Created Training: {}", saved);
        return saved;
    }

    @Override
    public Optional<Training> findById(Long id) { return trainingDao.findById(id); }

    @Override
    public List<Training> findAll() { return trainingDao.findAll(); }
}
