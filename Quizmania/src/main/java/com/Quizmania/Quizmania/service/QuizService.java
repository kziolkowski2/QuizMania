package com.Quizmania.Quizmania.service;

import com.Quizmania.Quizmania.repository.*;
import com.Quizmania.Quizmania.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.*;

@Service
public class QuizService {
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final QuestionSetRepository questionSetRepository;
    private final QuizRepository quizRepository;

    @Autowired
    public QuizService(AnswerRepository answerRepository, QuestionRepository questionRepository,
                       QuestionSetRepository questionSetRepository, QuizRepository quizRepository) {
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
        this.questionSetRepository = questionSetRepository;
        this.quizRepository = quizRepository;
    }


}