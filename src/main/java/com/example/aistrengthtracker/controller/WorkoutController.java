package com.example.aistrengthtracker.controller;

import com.example.aistrengthtracker.model.Workout;
import com.example.aistrengthtracker.service.WorkoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/workouts")
@RequiredArgsConstructor
public class WorkoutController {

    private final WorkoutService workoutService;

    @PostMapping
    public ResponseEntity<Workout> create(@RequestBody Workout workout) {
        Workout saved = workoutService.createWorkout(workout);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();
        return ResponseEntity.created(location).body(saved); // 201 + Location header
    }

    @GetMapping
    public List<Workout> getAll() {
        return workoutService.getAllWorkouts();
    }

    @GetMapping("/{id}")
    public Workout getById(@PathVariable Long id) {
        return workoutService.getWorkoutById(id);
    }
}