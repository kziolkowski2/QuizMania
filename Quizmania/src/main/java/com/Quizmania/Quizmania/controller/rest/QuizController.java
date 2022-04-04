package com.Quizmania.Quizmania.controller.rest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import com.Quizmania.Quizmania.domain.*;
import com.Quizmania.Quizmania.service.QuizService;

@RestController
public class QuizController {
    final QuizService quizService;

    public QuizController(QuizService quizService){
        this.quizService = quizService;
    }

    @GetMapping("")
    public Page<Quiz> findAll(Pageable pageable){
        return quizService.findAll(pageable);
    }

//    @GetMapping("/search")
//    public Page<Quiz>
}
