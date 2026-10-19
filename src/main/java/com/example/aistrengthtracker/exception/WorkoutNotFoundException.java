package com.example.aistrengthtracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Spring translates this exception into an HTTP 404 automatically
@ResponseStatus(HttpStatus.NOT_FOUND)
public class WorkoutNotFoundException extends RuntimeException {

    public WorkoutNotFoundException(Long id) {
        super("Workout not found with id: " + id);
    }
}