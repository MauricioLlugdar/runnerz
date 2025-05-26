package dev.maullu.runnerz.run;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component // We tell Spring to be in charge of this class
public class RunJsonDataLoader implements CommandLineRunner { // CommandLineRunner is used to execute code after the application has started

    private final RunRepository runRepository;
    private final ObjectMapper objectMapper;

    public RunJsonDataLoader(RunRepository runRepository, ObjectMapper objectMapper) {
        this.runRepository = runRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception { // This is the method that is called
        if (runRepository.count() == 0) {
            try (InputStream inputStream = getClass().getResourceAsStream("/data/runs.json")) { // Load the JSON file from resources
                if (inputStream == null) {
                    throw new IllegalArgumentException("Data file not found");
                }
                Runs allRuns = objectMapper.readValue(inputStream, Runs.class);
                runRepository.saveAll(allRuns.runs());
            } catch (Exception e) {
                throw new RuntimeException("Failed to load runs from JSON", e);
            }
        }
    }
}
