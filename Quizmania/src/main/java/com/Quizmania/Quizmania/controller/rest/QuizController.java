package com.Quizmania.Quizmania.controller.rest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import com.Quizmania.Quizmania.domain.*;
import com.Quizmania.Quizmania.service.QuizService;

import java.util.List;

@RestController
@RequestMapping(QuizController.ROOT_MAPPING)
public class QuizController {
    final QuizService quizService;
    public static final String ROOT_MAPPING = "/api/quizzes";

    public QuizController(QuizService quizService){
        this.quizService = quizService;
    }

    @GetMapping("")
    public List<Quiz> findAll(){
        return quizService.findAll();
    }

}
