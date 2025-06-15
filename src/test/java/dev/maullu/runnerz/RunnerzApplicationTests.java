package dev.maullu.runnerz;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

@SpringBootTest
class RunnerzApplicationTests {

	// Add the @Testcontainers annotation to enable Testcontainers support
	@Container //Docker container in this case
	@ServiceConnection //It configures automatically the testDb
	static PostgreSQLContainer<?> postgreSQL = new PostgreSQLContainer<>("postgres:17"); // I want to use the same than in production

	@Test
	void contextLoads() {
	}

}
