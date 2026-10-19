package com.example.aistrengthtracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "workout_sets")
@Getter
@Setter
@NoArgsConstructor
public class WorkoutSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String exerciseName;

    private Double weight;

    private Integer reps;

    // Owning side: creates the "workout_id" foreign key column.
    // LAZY: the parent is only loaded when actually accessed.
    // @JsonIgnore: breaks the JSON cycle (Workout -> sets -> workout -> ...)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "workout_id")
    @JsonIgnore
    private Workout workout;
}