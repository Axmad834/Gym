package com.example.demo.service;

import com.example.demo.model.Training;

import java.util.*;

public interface TrainingService {
    Training create(Training training);
    Optional<Training> findById(Long id);
    List<Training> findAll();
}
