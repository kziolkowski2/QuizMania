package com.Quizmania.Quizmania.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.Quizmania.Quizmania.repository.*;
import com.Quizmania.Quizmania.domain.*;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class BrowserService {
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final QuizRepository quizRepository;

    @Autowired
    public BrowserService(AnswerRepository answerRepository, QuestionRepository questionRepository,
                       QuizRepository quizRepository) {
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
        this.quizRepository = quizRepository;
    }

    public Optional<Quiz> find(Long id){
        return quizRepository.findById(id);
    }
    public Question findQuestion(Long id, int questionIndex){
        return quizRepository.findById(id).get().getQuestionList().get(questionIndex);
    }
    public List<Quiz> findAll() {
        return quizRepository.findAll();
    }

    public Page<Quiz> findPageByKeyword(String keyword, int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber - 1,5);
        return quizRepository.findByNameContaining(keyword, pageable);
    }

    public Page<Quiz> findPageByKeywordWithSort(String keyword, String field, String direction, int pageNumber) {
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(field).ascending() : Sort.by(field).descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, 5, sort);
        return quizRepository.findByNameContaining(keyword, pageable);
    }
}
