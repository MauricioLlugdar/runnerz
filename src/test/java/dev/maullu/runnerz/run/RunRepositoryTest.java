package dev.maullu.runnerz.run;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.AutoConfigureDataJdbc;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@DataJdbcTest //Test specifically jdbc
class RunRepositoryTest {

    @Container //Docker container in this case
    @ServiceConnection //It configures automatically the testDb
    static PostgreSQLContainer<?> postgreSQL = new PostgreSQLContainer<>("postgres:17"); // I want to use the same than in production

    @Autowired // It tells spring to inject the class RunRepository in this test class
    RunRepository runRepository;
    /*
    @Test
    void connectionEstablished(){
        assertTrue(postgreSQL.isCreated());
        assertTrue(postgreSQL.isRunning());
    } */
}