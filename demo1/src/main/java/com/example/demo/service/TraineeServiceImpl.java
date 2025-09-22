package com.example.demo.service;

import com.example.demo.dao.TraineeDao;
import com.example.demo.dao.TrainerDao;
import com.example.demo.model.Trainee;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TraineeServiceImpl implements TraineeService {
    private static final Logger log = LoggerFactory.getLogger(TraineeServiceImpl.class);

    private TraineeDao traineeDao;
    private TrainerDao trainerDao;
    private UsernamePasswordGenerator generator;

    @Autowired
    public void setTraineeDao(TraineeDao traineeDao) { this.traineeDao = traineeDao; }

    @Autowired
    public void setTrainerDao(TrainerDao trainerDao) { this.trainerDao = trainerDao; }

    @Autowired
    public void setGenerator(UsernamePasswordGenerator generator) { this.generator = generator; }

    @Override
    public Trainee create(Trainee t) {
        String username = generator.generateUniqueUsername(t.getFirstName(), t.getLastName(), uname ->
                traineeDao.findByUsername(uname).isPresent() || trainerDao.findByUsername(uname).isPresent());
        t.setUsername(username);
        t.setPassword(generator.generatePassword());
        Trainee saved = traineeDao.save(t);
        log.info("Created Trainee: {}", saved);
        return saved;
    }

    @Override
    public Optional<Trainee> findById(Long id) { return traineeDao.findById(id); }

    @Override
    public Optional<Trainee> findByUsername(String username) { return traineeDao.findByUsername(username); }

    @Override
    public List<Trainee> findAll() { return traineeDao.findAll(); }
}
