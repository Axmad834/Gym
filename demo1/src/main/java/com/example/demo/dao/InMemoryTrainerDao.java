package com.example.demo.dao;
import com.example.demo.model.Trainer;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryTrainerDao implements TrainerDao {
    private static final Logger log = LoggerFactory.getLogger(InMemoryTrainerDao.class);
    private Map<Long, Trainer> storage;
    private final AtomicLong idGen = new AtomicLong(0);

    @Autowired
    public void setStorage(@Qualifier("trainersStorage") Map<Long, Trainer> storage) {
        this.storage = storage;
        // set idGen initial value from existing max
        storage.keySet().stream().max(Long::compareTo).ifPresent(max -> idGen.set(max));
    }

    @Override
    public Trainer save(Trainer t) {
        if (t.getTrainerId() == null) t.setTrainerId(idGen.incrementAndGet());
        storage.put(t.getTrainerId(), t);
        log.info("Saved trainer: {}", t);
        return t;
    }

    @Override
    public Optional<Trainer> findById(Long id) { return Optional.ofNullable(storage.get(id)); }

    @Override
    public Optional<Trainer> findByUsername(String username) {
        return storage.values().stream().filter(tr -> username.equals(tr.getUsername())).findFirst();
    }

    @Override
    public Optional<Trainer> findByFirstAndLastName(String first, String last) {
        return storage.values().stream()
                .filter(tr -> first.equals(tr.getFirstName()) && last.equals(tr.getLastName()))
                .findFirst();
    }

    @Override
    public List<Trainer> findAll() { return new ArrayList<>(storage.values()); }

    @Override
    public void delete(Long id) { storage.remove(id); log.info("Deleted trainer id={}", id); }
}