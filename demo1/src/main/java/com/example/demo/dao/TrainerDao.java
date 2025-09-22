package com.example.demo.dao;

import com.example.demo.model.Trainer;

import java.util.*;

public interface TrainerDao {
    Trainer save(Trainer t);
    Optional<Trainer> findById(Long id);
    Optional<Trainer> findByUsername(String username);
    Optional<Trainer> findByFirstAndLastName(String first, String last);
    List<Trainer> findAll();
    void delete(Long id);
}
