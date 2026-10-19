package com.sergillobet.ai_strength_tracker.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "workouts")
@Getter
@Setter
@NoArgsConstructor
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Explicit column name to avoid clashes with SQL reserved words
    @Column(name = "workout_date", nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private String name;

    // "mappedBy" = the WorkoutSet side owns the foreign key.
    // cascade ALL: saving/deleting a Workout also saves/deletes its sets.
    // orphanRemoval: a set removed from the list is deleted from the DB.
    @OneToMany(mappedBy = "workout", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WorkoutSet> sets = new ArrayList<>();

    // Helper that keeps both sides of the relationship in sync
    public void addSet(WorkoutSet set) {
        sets.add(set);
        set.setWorkout(this);
    }
}