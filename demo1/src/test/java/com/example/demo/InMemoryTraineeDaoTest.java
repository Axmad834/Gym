package com.example.demo.dao;

import com.example.demo.model.Trainee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTraineeDaoTest {

    private InMemoryTraineeDao dao;

    @BeforeEach
    void setUp() {
        dao = new InMemoryTraineeDao();
        dao.setStorage(new HashMap<>());
    }

    @Test
    void testSaveAndFindById() {
        Trainee t = new Trainee(null, "John", "Doe", null, null,
                LocalDate.of(2000, 1, 1), "Addr", true);
        dao.save(t);

        assertNotNull(t.getTraineeId());
        assertEquals(t, dao.findById(t.getTraineeId()).orElse(null));
    }

    @Test
    void testFindAll() {
        dao.save(new Trainee(null, "A", "B", null, null, LocalDate.now(), "Addr", true));
        dao.save(new Trainee(null, "C", "D", null, null, LocalDate.now(), "Addr", true));

        List<Trainee> all = dao.findAll();
        assertEquals(2, all.size());
    }

    @Test
    void testDelete() {
        Trainee t = new Trainee(null, "X", "Y", null, null, LocalDate.now(), "Addr", true);
        dao.save(t);
        dao.delete(t.getTraineeId());

        assertTrue(dao.findById(t.getTraineeId()).isEmpty());
    }
}
