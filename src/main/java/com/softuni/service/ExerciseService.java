package com.softuni.service;

import com.softuni.model.entities.Exercise;
import com.softuni.model.service.ExerciseServiceModel;

import java.util.List;

public interface ExerciseService {
    void addExercise(ExerciseServiceModel exerciseServiceModel);

    List<String> findAllNames();

    boolean check(String exercise);

    Exercise findByName(String name);
}
