package com.Quizmania.Quizmania.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.Quizmania.Quizmania.repository.*;
import com.Quizmania.Quizmania.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class QuizService {
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final QuizRepository quizRepository;

    @Autowired
    public QuizService(AnswerRepository answerRepository, QuestionRepository questionRepository,
                       QuizRepository quizRepository) {
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
        this.quizRepository = quizRepository;
    }

    public Page<Quiz> findAll(Pageable pageable) {
        return quizRepository.findAll(pageable);
    }

    public Quiz save(Quiz quiz) {
        //quiz.setCreatedBy(user);
        return quizRepository.save(quiz);
    }
//    public void setTimeLimit(int timeLimit) {
//        quizRepository.timeLimit = timeLimit;
//        if(timeLimit > 0)
//            quizRepository.isTimed = true;
//        else
//            quizRepository.isTimed = false;
//    }
//    public void addQuestion(Question question) {
//        questionList.add(question);
//        question.setParentQuestionSet(this);
//    }
//
//    public void removeQuestion(int index) {
//        questionList.remove(index);
//    }

}
