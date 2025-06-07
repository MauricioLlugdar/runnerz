package dev.maullu.runnerz.run;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.AutoConfigureDataJdbc;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@DataJdbcTest //Test specifically jdbc
class RunRepositoryTest {

    @Container //Docker container in this case
    @ServiceConnection //It configures automatically the testDb
    static PostgreSQLContainer<?> postgreSQL = new PostgreSQLContainer<>("postgres:17"); // I want to use the same than in production

    @Autowired // It tells spring to inject the class RunRepository in this test class
    RunRepository runRepository;

    @Test
    void connectionEstablished(){
        assertTrue(postgreSQL.isCreated());
        assertTrue(postgreSQL.isRunning());
    }


    @BeforeEach
    void setUp() {
        runRepository.saveAll(List.of(new Run(
                1,
                "Test Run",
                java.time.LocalDateTime.of(2023, 10, 1, 9, 0),
                java.time.LocalDateTime.of(2023, 10, 1, 10, 0),
                5,
                Location.OUTDOOR,
                null
            ),
            new Run(
                2,
                "Another Test Run",
                java.time.LocalDateTime.of(2023, 10, 2, 9, 0),
                java.time.LocalDateTime.of(2023, 10, 2, 10, 0),
                10,
                Location.INDOOR,
                null
            ),
            new Run(
                3,
                "Third Test Run",
                java.time.LocalDateTime.of(2023, 10, 3, 9, 0),
                java.time.LocalDateTime.of(2023, 10, 3, 10, 0),
                15,
                Location.OUTDOOR,
                null
            ))
        );
    }

    @Test
    void findAll() {
        List<Run> runs = runRepository.findAll();
        assertEquals(3, runs.size());
    }

    @Test
    void findById() {
        Run run = runRepository.findById(1).orElseThrow();
        assertEquals("Test Run", run.title());
    }

    @Test
    void findAllByLocation() {
        List<Run> outdoorRuns = runRepository.findAllByLocation("OUTDOOR");
        assertEquals(2, outdoorRuns.size());
        assertTrue(outdoorRuns.stream().allMatch(run -> run.location() == Location.OUTDOOR));

        List<Run> indoorRuns = runRepository.findAllByLocation("INDOOR");
        assertEquals(1, indoorRuns.size());
        assertTrue(indoorRuns.stream().allMatch(run -> run.location() == Location.INDOOR));
    }


}