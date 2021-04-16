package com.softuni.web;

import com.softuni.model.binding.HomeworkAddBindingModel;
import com.softuni.service.ExerciseService;
import com.softuni.service.HomeworkService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/homeworks")
public class HomeworkController {
    private final HomeworkService homeworkService;
    private final ExerciseService exerciseService;

    public HomeworkController(HomeworkService homeworkService, ExerciseService exerciseService) {
        this.homeworkService = homeworkService;
        this.exerciseService = exerciseService;
    }

    @GetMapping("/add")
    public String add(Model model) {
        if (!model.containsAttribute("homeworkAddBindingModel")) {
            model.addAttribute("homeworkAddBindingModel", new HomeworkAddBindingModel());
            model.addAttribute("isLate", false);
        }
        model.addAttribute("exNames", exerciseService.findAllNames());
        return "homework-add";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid HomeworkAddBindingModel homeworkAddBindingModel, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("homeworkAddBindingModel", homeworkAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.homeworkAddBindingModel", bindingResult);

            return "redirect:add";
        }

        boolean isLate = exerciseService.check(homeworkAddBindingModel.getExercise());

        if (isLate) {
            redirectAttributes.addFlashAttribute("homeworkAddBindingModel", homeworkAddBindingModel);
            redirectAttributes.addFlashAttribute("isLate", true);

            return "redirect:add";

        }
        homeworkService.addHomework(homeworkAddBindingModel.getExercise(), homeworkAddBindingModel.getGitAddress());
        return "redirect:/";
    }
}
