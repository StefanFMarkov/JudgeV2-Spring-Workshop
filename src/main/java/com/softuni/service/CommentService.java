package com.softuni.service;

import com.softuni.model.service.CommentServiceModel;


import java.util.List;
import java.util.Map;
import java.util.Set;

public interface CommentService {
    void add(CommentServiceModel commentServiceModel, Long homeworkId);

    Double findAvgScore();

    Map<Integer,Integer> findScoreMap();

    Set<String> findTop3();

}
