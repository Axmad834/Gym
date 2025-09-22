package com.example.demo.dao;

import com.example.demo.model.Training;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryTrainingDao implements TrainingDao {
    private static final Logger log = LoggerFactory.getLogger(InMemoryTrainingDao.class);
    private Map<Long, Training> storage;
    private final AtomicLong idGen = new AtomicLong(0);

    @Autowired
    public void setStorage(@Qualifier("trainingsStorage") Map<Long, Training> storage) {
        this.storage = storage;
        storage.keySet().stream().max(Long::compareTo).ifPresent(max -> idGen.set(max));
    }

    @Override
    public Training save(Training t) {
        if (t.getTrainingId() == null) t.setTrainingId(idGen.incrementAndGet());
        storage.put(t.getTrainingId(), t);
        log.info("Saved training: {}", t);
        return t;
    }

    @Override
    public Optional<Training> findById(Long id) { return Optional.ofNullable(storage.get(id)); }

    @Override
    public List<Training> findAll() { return new ArrayList<>(storage.values()); }

    @Override
    public void delete(Long id) { storage.remove(id); log.info("Deleted training id={}", id); }
}