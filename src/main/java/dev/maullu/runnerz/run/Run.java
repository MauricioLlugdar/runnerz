package dev.maullu.runnerz.run;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record Run( // In a record the attributes could be used as func. bcs that's the getter
        Integer id,
        @NotEmpty //Using validation
        String title,
        LocalDateTime startedOn,
        LocalDateTime completedOn,
        @Positive
        Integer miles,
        Location location
) { // This compact constructor let us validate or modify values of the instances
    public Run{
        if (startedOn.isAfter(completedOn)){
            throw new IllegalArgumentException("completedOn must be after startedOn");
        }
    }
}