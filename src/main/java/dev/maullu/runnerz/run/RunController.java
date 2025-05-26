package dev.maullu.runnerz.run;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/runs")
public class RunController {
    private final RunRepository runRepository;

    // Use Inversion of control to let spring decide if its necessary to create another object
    public RunController(RunRepository runRepository) {
        this.runRepository = runRepository;
    }

    @GetMapping("")
    List<Run> findAll() {
        return runRepository.findAll();
    }

    @GetMapping("/{id}")
    Run findById(@PathVariable Integer id){
        Optional<Run> run = runRepository.findById(id);
        if(run.isEmpty()){
            throw new RunNotFoundException(); // id run not found
        }
        return run.get(); // Get content of the optional
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void addRun(@RequestBody Run run){
        runRepository.addRun(run);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{id}")
    void updateRun(@RequestBody Run run, @PathVariable Integer id){
        runRepository.updateRun(run, id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void deleteRun(@PathVariable Integer id){
        runRepository.deleteRun(id);
    }
}
