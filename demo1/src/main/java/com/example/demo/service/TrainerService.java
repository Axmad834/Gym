package com.example.demo.service;

import com.example.demo.model.Trainer;

import java.util.*;

public interface TrainerService {
    Trainer create(Trainer trainer);
    Optional<Trainer> findById(Long id);
    Optional<Trainer> findByUsername(String username);
    List<Trainer> findAll();
}
