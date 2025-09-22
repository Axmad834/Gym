import com.example.demo.dao.TraineeDao;
import com.example.demo.model.Trainee;
import com.example.demo.service.TraineeServiceImpl;
import com.example.demo.service.UsernamePasswordGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TraineeServiceImplTest {

    private TraineeServiceImpl service;
    private TraineeDao dao;
    private UsernamePasswordGenerator generator;

    @BeforeEach
    void setUp() {
        dao = mock(TraineeDao.class);
        generator = mock(UsernamePasswordGenerator.class);
        service = new TraineeServiceImpl();
        service.setTraineeDao(dao);
        service.setGenerator(generator);
    }

    @Test
    void testCreate() {
        Trainee t = new Trainee(null, "John", "Smith", null, null,
                LocalDate.of(1995,1,1), "Addr", true);

        when(generator.generateUniqueUsername(anyString(), anyString(), any())).thenReturn("John.Smith");
        when(generator.generatePassword()).thenReturn("1234567890");
        when(dao.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        Trainee created = service.create(t);

        assertEquals("John.Smith", created.getUsername());
        assertEquals("1234567890", created.getPassword());
        verify(dao, times(1)).save(created);
    }
}

