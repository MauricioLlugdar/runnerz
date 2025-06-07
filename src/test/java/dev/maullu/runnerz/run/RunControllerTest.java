package dev.maullu.runnerz.run;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//INTEGRATION TEST

@Testcontainers // Scan for container annotation
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RunControllerTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:17.0");

    @Autowired
    TestRestTemplate restTemplate;


    // It should find 10 runs in the database because we have created 10 runs with runs.json in the db
    @Test
    void shouldFindAllRuns(){
        // "/api/runs"
        Run[] runs = restTemplate.getForObject("/api/runs", Run[].class);
        assertEquals(10, runs.length, "There should be 10 runs in the database");
    }

}