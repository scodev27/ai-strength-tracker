package com.example.aistrengthtracker.service;

import com.example.aistrengthtracker.exception.WorkoutNotFoundException;
import com.example.aistrengthtracker.model.Workout;
import com.example.aistrengthtracker.repository.WorkoutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor // constructor injection for final fields (preferred over @Autowired)
public class WorkoutService {

    private final WorkoutRepository workoutRepository;

    @Transactional
    public Workout createWorkout(Workout workout) {
        // The JSON body only fills one side of the relationship,
        // so we link each set back to its parent before saving.
        workout.getSets().forEach(set -> set.setWorkout(workout));
        return workoutRepository.save(workout);
    }

    @Transactional(readOnly = true)
    public List<Workout> getAllWorkouts() {
        return workoutRepository.findAllByOrderByDateDesc();
    }

    @Transactional(readOnly = true)
    public Workout getWorkoutById(Long id) {
        return workoutRepository.findById(id)
                .orElseThrow(() -> new WorkoutNotFoundException(id));
    }
}