package dev.maullu.runnerz.run;

import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface RunRepository extends ListCrudRepository<Run, Integer> { // We are creating a repo around Run type with an Integer id

    List<Run> findAllByLocation(String location);

}
