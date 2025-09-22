package com.example.demo.service;

import com.example.demo.model.Trainee;

import java.util.*;

public interface TraineeService {
    Trainee create(Trainee t);
    Optional<Trainee> findById(Long id);
    Optional<Trainee> findByUsername(String username);
    List<Trainee> findAll();
}