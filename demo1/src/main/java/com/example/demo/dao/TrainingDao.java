package com.example.demo.dao;

import com.example.demo.model.Training;
import java.util.*;

public interface TrainingDao {
    Training save(Training t);
    Optional<Training> findById(Long id);
    List<Training> findAll();
    void delete(Long id);
}
