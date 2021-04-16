package com.softuni.service;

import com.softuni.model.entities.Homework;
import com.softuni.model.service.HomeworkServiceModel;

public interface HomeworkService {
    void addHomework(String exercise, String gitAddress);

    HomeworkServiceModel findHomeworkForScoring();

    Homework findById(Long homeworkId);
}
