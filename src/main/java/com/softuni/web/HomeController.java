package com.softuni.web;

import com.softuni.security.CurrentUser;
import com.softuni.service.CommentService;
import com.softuni.service.ExerciseService;
import com.softuni.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    private final ExerciseService exerciseService;
    private final CurrentUser currentUser;
    private final CommentService commentService;
    private final UserService userService;

    public HomeController(ExerciseService exerciseService, CurrentUser currentUser, CommentService commentService, UserService userService) {
        this.exerciseService = exerciseService;
        this.currentUser = currentUser;
        this.commentService = commentService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String index(Model model) {
        if (currentUser.isAnonymous()) {
            return "index";
        }

        model.addAttribute("exercises", exerciseService.findAllNames());
        model.addAttribute("avg", this.commentService.findAvgScore());
        model.addAttribute("usersCount", userService.findUserCount());
        model.addAttribute("scoreMap", commentService.findScoreMap());
        model.addAttribute("allTopStudents",commentService.findTop3());


        return "home";
    }

}
