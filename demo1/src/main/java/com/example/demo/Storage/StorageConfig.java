package com.example.demo.Storage;

import com.example.demo.model.Trainee;
import com.example.demo.model.Trainer;
import com.example.demo.model.Training;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
@PropertySource("classpath:config/storage.properties")
public class StorageConfig {
    @Bean(name  = "trainersStorage")
    public Map<Long, Trainer> trainerStrorage(){
        return new ConcurrentHashMap<>();
    }

    @Bean(name = "traineesStorage")
    public Map<Long, Trainee> traineeStrorage(){
        return new ConcurrentHashMap<>();
    }

    @Bean(name = "trainingsStorage")
    public Map<Long, Training> trainingStrorage(){
        return new ConcurrentHashMap<>();
    }

    @Bean
    @ConfigurationProperties(prefix = "storage.paths")
    public Map<String,String> StoragePaths(){
        return new HashMap<>();
    }


}
