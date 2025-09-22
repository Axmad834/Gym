package com.example.demo.dao;

import com.example.demo.model.Trainee;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryTraineeDao implements TraineeDao {
    private static final Logger log = LoggerFactory.getLogger(InMemoryTraineeDao.class);
    private Map<Long, Trainee> storage;
    private final AtomicLong idGen = new AtomicLong(0);

    @Autowired
    public void setStorage(@Qualifier("traineesStorage") Map<Long, Trainee> storage) {
        this.storage = storage;
        storage.keySet().stream().max(Long::compareTo).ifPresent(max -> idGen.set(max));
    }

    @Override
    public Trainee save(Trainee t) {
        if (t.getTraineeId() == null) t.setTraineeId(idGen.incrementAndGet());
        storage.put(t.getTraineeId(), t);
        log.info("Saved trainee: {}", t);
        return t;
    }

    @Override
    public Optional<Trainee> findById(Long id) { return Optional.ofNullable(storage.get(id)); }

    @Override
    public Optional<Trainee> findByUsername(String username) {
        return storage.values().stream().filter(tr -> username.equals(tr.getUsername())).findFirst();
    }

    @Override
    public Optional<Trainee> findByFirstAndLastName(String first, String last) {
        return storage.values().stream()
                .filter(tr -> first.equals(tr.getFirstName()) && last.equals(tr.getLastName()))
                .findFirst();
    }

    @Override
    public List<Trainee> findAll() { return new ArrayList<>(storage.values()); }

    @Override
    public void delete(Long id) { storage.remove(id); log.info("Deleted trainee id={}", id); }
}