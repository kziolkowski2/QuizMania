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

    public Optional<Quiz> find(Long id){
        return quizRepository.findById(id);
    }
    public Question save(Question question){
        return questionRepository.save(question);
    }
    public Iterable<Question> findAllQuestions(){
        return questionRepository.findAll();
    }
    public List<Question> findAllUnassignedQuestions(){
        return questionRepository.findByParentQuizIsNull();
    }

    public List<Quiz> findAll() {
        return quizRepository.findAll();
    }
    public Optional<Question> findQuestionById(Long id){
        return questionRepository.findById(id);
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

    public Quiz save(Quiz quiz) {
        //quiz.setCreatedBy(user);
        return quizRepository.save(quiz);
    }

    public void initialize() {
//        List<Answer> testAnswers = new ArrayList<>();
//        Question testQuestion = new Question("Ile to 2+3?",QuestionTypeEnum.CLOSED,testAnswers);
//        Answer answer12 = new Answer("1", false, testQuestion);
//        Answer answer22 = new Answer("2", false, testQuestion);
//        Answer answer32 = new Answer("5", false, testQuestion);
//        testAnswers.add(answer12);
//        testAnswers.add(answer22);
//        testAnswers.add(answer32);
//        testQuestion.setAnswerList(testAnswers);
//        answerRepository.save(answer12);
//        answerRepository.save(answer22);
//        answerRepository.save(answer32);
//        questionRepository.save(testQuestion);
//        List<Question> questions1 = new ArrayList<>();
//        List<Question> questions2 = new ArrayList<>();
//        Quiz quiz1 = new Quiz("Matura z matematyki 2022", 0, CategoryEnum.MATEMATYKA, LanguageEnum.PL, LocalDate.now(), questions1 );
//        Quiz quiz2 = new Quiz("quiz 2", 0, CategoryEnum.HISTORIA, LanguageEnum.EN, LocalDate.now(), questions2 );
//
//        List<Answer> answers1 = new ArrayList<>();
//        List<Answer> answers2 = new ArrayList<>();
//        List<Answer> answers3 = new ArrayList<>();
//        Question question1 = new Question("Ile to 1+1?", QuestionTypeEnum.CLOSED, quiz1, answers1);
//        Question question2 = new Question("Ile to 4/2?", QuestionTypeEnum.CLOSED, quiz1, answers2);
//        Question question3 = new Question("Ile to 3-3?", QuestionTypeEnum.OPEN, quiz1, answers3);
//
//        Answer answer1 = new Answer("2", true, question1);
//        Answer answer2 = new Answer("0", false, question1);
//        Answer answer3 = new Answer("3", false, question1);
//
//        Answer answer4 = new Answer("2", true, question2);
//        Answer answer5 = new Answer("8", false, question2);
//        Answer answer6 = new Answer("1", false, question2);
//
//        Answer answer7 = new Answer("0", true, question3);
//
//        answers1.add(answer1);
//        answers1.add(answer2);
//        answers1.add(answer3);
//
//        answers2.add(answer4);
//        answers2.add(answer5);
//        answers2.add(answer6);
//
//        answers3.add(answer7);
//
//        question1.setAnswerList(answers1);
//        question2.setAnswerList(answers2);
//        question3.setAnswerList(answers3);
//
//        questions1.add(question1);
//        questions1.add(question2);
//        questions1.add(question3);
//
//        quiz1.setQuestionList(questions1);
//        quiz2.setQuestionList(questions2);
//
//
//        answerRepository.save(answer1);
//        answerRepository.save(answer2);
//        answerRepository.save(answer3);
//        answerRepository.save(answer4);
//        answerRepository.save(answer5);
//        answerRepository.save(answer6);
//        answerRepository.save(answer7);
//
//        questionRepository.save(question1);
//        questionRepository.save(question2);
//        questionRepository.save(question3);
//
//        quizRepository.save(quiz1);
//        quizRepository.save(quiz2);


    }

}