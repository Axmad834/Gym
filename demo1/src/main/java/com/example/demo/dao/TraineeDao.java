package com.example.demo.dao;


import com.example.demo.model.Trainee;

import java.util.*;

public interface TraineeDao {
    Trainee save(Trainee t);
    Optional<Trainee> findById(Long id);
    Optional<Trainee> findByUsername(String username);
    Optional<Trainee> findByFirstAndLastName(String first, String last);
    List<Trainee> findAll();
    void delete(Long id);
}