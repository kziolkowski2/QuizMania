package com.Quizmania.Quizmania.controller.rest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import com.Quizmania.Quizmania.domain.*;
import com.Quizmania.Quizmania.service.QuizService;

@RestController
@RequestMapping(QuizController.ROOT_MAPPING)
public class QuizController {
    final QuizService quizService;
    public static final String ROOT_MAPPING = "/api/quizes";

    public QuizController(QuizService quizService){
        this.quizService = quizService;
    }

    @GetMapping("")
    public Page<Quiz> findAll(Pageable pageable){
        return quizService.findAll(pageable);
    }
    @PostMapping("")
    void addQuiz(@RequestBody Quiz quiz){
        quizService.save(quiz);
    }

//    @GetMapping("/search")
//    public Page<Quiz>
}
