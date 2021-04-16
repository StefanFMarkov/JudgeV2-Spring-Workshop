package com.softuni.service.impl;

import com.softuni.model.entities.Comment;
import com.softuni.model.entities.User;
import com.softuni.model.service.CommentServiceModel;
import com.softuni.repository.CommentRepository;
import com.softuni.security.CurrentUser;
import com.softuni.service.CommentService;
import com.softuni.service.HomeworkService;
import com.softuni.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final UserService userService;
    private final CurrentUser currentUser;
    private final ModelMapper modelMapper;
    private final HomeworkService homeworkService;

    public CommentServiceImpl(CommentRepository commentRepository, UserService userService,
                              CurrentUser currentUser, ModelMapper modelMapper,
                              HomeworkService homeworkService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.currentUser = currentUser;
        this.modelMapper = modelMapper;
        this.homeworkService = homeworkService;
    }

    @Override
    public void add(CommentServiceModel commentServiceModel, Long homeworkId) {

        Comment comment = modelMapper.map(commentServiceModel, Comment.class);
        comment.setAuthor(userService.findById(currentUser.getId()));
        comment.setHomework(homeworkService.findById(homeworkId));

        this.commentRepository.save(comment);
    }

    @Override
    public Double findAvgScore() {
        return commentRepository.findAvgScore();
    }

    @Override
    public Map<Integer, Integer> findScoreMap() {
        Map<Integer, Integer> scoreMap = initScoreMap();

        commentRepository.findAll().forEach(comment -> {
            Integer score = comment.getScore();
            scoreMap.put(score, scoreMap.get(score) + 1);
        });

        return scoreMap;
    }

    @Override
    public Set<String> findTop3() {
        return this.commentRepository.findTop3ByAuthor_OrderByScore();
    }


    private Map<Integer, Integer> initScoreMap() {
        Map<Integer, Integer> scoreMap = new HashMap<>();
        for (int i = 2; i <= 6; i++) {
            scoreMap.put(i, 0);
        }
        return scoreMap;
    }
}
