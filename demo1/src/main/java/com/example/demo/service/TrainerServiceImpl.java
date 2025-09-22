package com.example.demo.service;


import com.example.demo.dao.TraineeDao;
import com.example.demo.dao.TrainerDao;
import com.example.demo.model.Trainer;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainerServiceImpl implements TrainerService {
    private static final Logger log = LoggerFactory.getLogger(TrainerServiceImpl.class);

    private TrainerDao trainerDao;
    private TraineeDao traineeDao;
    private UsernamePasswordGenerator generator;

    @Autowired
    public void setTrainerDao(TrainerDao trainerDao) { this.trainerDao = trainerDao; }

    @Autowired
    public void setTraineeDao(TraineeDao traineeDao) { this.traineeDao = traineeDao; }

    @Autowired
    public void setGenerator(UsernamePasswordGenerator generator) { this.generator = generator; }

    @Override
    public Trainer create(Trainer trainer) {
        String username = generator.generateUniqueUsername(trainer.getFirstName(), trainer.getLastName(), uname ->
                trainerDao.findByUsername(uname).isPresent() || traineeDao.findByUsername(uname).isPresent());
        trainer.setUsername(username);
        trainer.setPassword(generator.generatePassword());
        Trainer saved = trainerDao.save(trainer);
        log.info("Created Trainer: {}", saved);
        return saved;
    }

    @Override
    public Optional<Trainer> findById(Long id) { return trainerDao.findById(id); }

    @Override
    public Optional<Trainer> findByUsername(String username) { return trainerDao.findByUsername(username); }

    @Override
    public List<Trainer> findAll() { return trainerDao.findAll(); }
}
