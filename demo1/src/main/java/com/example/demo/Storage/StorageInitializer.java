package com.example.demo.Storage;

import com.example.demo.model.Trainee;
import com.example.demo.model.Trainer;
import com.example.demo.model.Training;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;


import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class StorageInitializer {
    private static final Logger log = LoggerFactory.getLogger(StorageInitializer.class);

    private final Map<String,String> storagePaths;
    private final ResourceLoader resourceLoader;
    private final ObjectMapper objectMapper;

    @Autowired
    public StorageInitializer(Map<String,String> storagePaths, ResourceLoader resourceLoader) {
        this.storagePaths = storagePaths;
        this.resourceLoader = resourceLoader;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
        this.objectMapper.disable(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS);
    }

    @SuppressWarnings("unchecked")
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (!(bean instanceof Map)) return bean;

        String key = beanName.replace("Storage", ""); // e.g., trainersStorage -> trainers
        String path = storagePaths.get(key);
        if (path == null) {
            // no file provided; skip
            log.info("No storage path configured for '{}'", key);
            return bean;
        }

        try {
            Resource res = resourceLoader.getResource(path);
            if (!res.exists()) {
                log.warn("Resource {} for storage '{}' not found", path, key);
                return bean;
            }
            try (InputStream is = res.getInputStream()) {
                if ("trainers".equals(key)) {
                    List<Trainer> list = objectMapper.readValue(is, new TypeReference<List<Trainer>>() {});
                    Map<Long, Trainer> map = (Map<Long, Trainer>) bean;
                    list.forEach(t -> map.put(t.getTrainerId(), t));
                    log.info("Loaded {} trainers from {}", list.size(), path);
                } else if ("trainees".equals(key)) {
                    List<Trainee> list = objectMapper.readValue(is, new TypeReference<List<Trainee>>() {});
                    Map<Long, Trainee> map = (Map<Long, Trainee>) bean;
                    list.forEach(t -> map.put(t.getTraineeId(), t));
                    log.info("Loaded {} trainees from {}", list.size(), path);
                } else if ("trainings".equals(key)) {
                    // read DTOs first (training references by ids), resolve into Training objects
                    List<TrainingDTO> dtos = objectMapper.readValue(is, new TypeReference<List<TrainingDTO>>() {});
                    Map<Long, Training> map = (Map<Long, Training>) bean;
                    // find trainers/trainees storages to resolve relations
                    Map<Long, Trainer> trainers = getMapBean("trainersStorage");
                    Map<Long, Trainee> trainees = getMapBean("traineesStorage");
                    for (TrainingDTO dto : dtos) {
                        Trainer tr = trainers.get(dto.getTrainerId());
                        Trainee te = trainees.get(dto.getTraineeId());
                        Training t = new Training(dto.getTrainingId(), te, tr,
                                dto.getTrainingName(), dto.getTrainingType(),
                                dto.getTrainingDate(), dto.getDuration());
                        map.put(t.getTrainingId(), t);
                    }
                    log.info("Loaded {} trainings from {}", dtos.size(), path);
                }
            }
        } catch (Exception e) {
            log.error("Failed to initialize storage '{}' from {}: {}", key, path, e.getMessage(), e);
        }
        return bean;
    }



    private static final Map<String, Object> registry = new HashMap<>();


    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        // register all Map beans so we can later resolve relations
        if (bean instanceof Map) {
            registry.put(beanName, bean);
        }
        return bean;
    }

    @SuppressWarnings("unchecked")
    private <T> Map<Long, T> getMapBean(String beanName) {
        Object o = registry.get(beanName);
        if (o instanceof Map) return (Map<Long, T>) o;
        return Collections.emptyMap();
    }

    public static class TrainingDTO {
        private Long trainingId;
        private Long traineeId;
        private Long trainerId;
        private String trainingName;
        private String trainingType;
        private java.time.LocalDate trainingDate;
        private int duration;

        public TrainingDTO() {}

        public Long getTrainingId() { return trainingId; }
        public void setTrainingId(Long trainingId) { this.trainingId = trainingId; }

        public Long getTraineeId() { return traineeId; }
        public void setTraineeId(Long traineeId) { this.traineeId = traineeId; }

        public Long getTrainerId() { return trainerId; }
        public void setTrainerId(Long trainerId) { this.trainerId = trainerId; }

        public String getTrainingName() { return trainingName; }
        public void setTrainingName(String trainingName) { this.trainingName = trainingName; }

        public String getTrainingType() { return trainingType; }
        public void setTrainingType(String trainingType) { this.trainingType = trainingType; }

        public java.time.LocalDate getTrainingDate() { return trainingDate; }
        public void setTrainingDate(java.time.LocalDate trainingDate) { this.trainingDate = trainingDate; }

        public int getDuration() { return duration; }
        public void setDuration(int duration) { this.duration = duration; }
    }




}









