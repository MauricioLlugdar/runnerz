package dev.maullu.runnerz.run;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Repository // We tell Spring to be in charge of this class
public class RunRepository {

    private static final Logger log = LoggerFactory.getLogger(RunRepository.class); // Show in terminal logs
    private final JdbcClient jdbcClient;

    public RunRepository(JdbcClient jdbcClient){
        this.jdbcClient = jdbcClient;
    }

    public List<Run> findAll(){
        return jdbcClient.sql("SELECT * FROM run")
                .query(Run.class) // Map the rows to a Run object
                .list(); // Returns a list of Run objects
    }

    public Optional<Run> findById(Integer id){
        return jdbcClient.sql("SELECT * FROM run WHERE run.id = ?")
                .param(id)
                .query(Run.class)
                .optional();
    }

    public void addRun(Run run) {
        var updated = jdbcClient.sql("INSERT INTO run (title, started_on, completed_on, miles, location) VALUES (?, ?, ?, ?, ?)")
                .param(run.title())
                .param(run.startedOn())
                .param(run.completedOn())
                .param(run.miles())
                .param(run.location().toString())
                .update();
        Assert.state(updated == 1, "Failed to add a run " + run.title() );
    }

    public void updateRun(Run run, Integer id){
        var updated = jdbcClient.sql("UPDATE run SET title = ?, started_on = ?, completed_on = ?, miles = ?, location = ? WHERE id = ?")
                .param(run.title())
                .param(run.startedOn())
                .param(run.completedOn())
                .param(run.miles())
                .param(run.location().toString())
                .param(id)
                .update();
        Assert.state(updated == 1, "Failed to update a run " + run.title() );
    }

    public void deleteRun(Integer id){
        var deleted = jdbcClient.sql("DELETE FROM run WHERE id = ?")
                .param(id)
                .update();
        Assert.state(deleted == 1, "Failed to delete a run with id=" + id );
    }

    public int count() {
        return jdbcClient.sql("SELECT COUNT(*) FROM run")
                .query(Integer.class)
                .single();
    }

    public void saveAll(List<Run> runs){
        runs.forEach(this::addRun); // For each run, add it to the repository
    }




}
