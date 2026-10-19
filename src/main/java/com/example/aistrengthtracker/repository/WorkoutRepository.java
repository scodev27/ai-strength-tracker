package com.example.aistrengthtracker.repository;

import com.example.aistrengthtracker.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {

    // Derived query: SELECT * FROM workouts ORDER BY workout_date DESC
    List<Workout> findAllByOrderByDateDesc();
}