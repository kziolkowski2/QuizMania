package com.Quizmania.Quizmania.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.Quizmania.Quizmania.domain.*;
import com.Quizmania.Quizmania.service.QuizService;

@Controller
public class QuizWebController {
    final QuizService quizService;

    public QuizWebController(QuizService quizService){
        this.quizService = quizService;
    }

    @GetMapping("/")
    public String home() { return "home"; }

    @GetMapping("/Create")
    public String create() { return "home"; }

    @PostMapping("/Create")
    public String create(Quiz quiz, BindingResult bindingResult,Model model){
        Quiz newQuiz;
        newQuiz = quizService.save(quiz);
        return "createQuiz";
    }

}
