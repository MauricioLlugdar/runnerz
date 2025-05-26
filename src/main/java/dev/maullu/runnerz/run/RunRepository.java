package dev.maullu.runnerz.run;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import java.util.Objects;
import java.util.Optional;

@Repository // We tell Spring to be in charge of this class
public class RunRepository {
    private List<Run> runs = new ArrayList<>();

    List<Run> findAll(){
        return runs;
    }

    Optional<Run> findById(Integer id){ // Optional is for operating w objects without having a null value present
        return runs.stream() // Use of Streams to use methods like filter, map, etc.
                .filter(run -> Objects.equals(run.id(), id))
                .findFirst();
    }

    void addRun(Run run){
        runs.add(run);
    }

    @PostConstruct //Initialization in the class
    private void init(){
        runs.add(new Run(1,
                "Monday Morning Run",
                java.time.LocalDateTime.now(),
                java.time.LocalDateTime.now().plusHours(1),
                5,
                Location.INDOOR));
        runs.add(new Run(2,
                "Evening Jog",
                java.time.LocalDateTime.now().minusDays(1),
                java.time.LocalDateTime.now().minusDays(1).plusHours(1),
                3,
                Location.OUTDOOR));
    }
}
