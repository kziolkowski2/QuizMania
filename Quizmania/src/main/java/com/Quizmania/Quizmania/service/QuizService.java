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

    public List<Quiz> findAll() {
        return quizRepository.findAll();
    }

    public Page<Quiz> findPage(int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber - 1,5);
        return quizRepository.findAll(pageable);
    }
    public Page<Quiz> findWithSort(String field, String direction, int pageNumber) {
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(field).ascending() : Sort.by(field).descending();

        Pageable pageable = PageRequest.of(pageNumber - 1, 5, sort);

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
    public void initialize() {
        LocalDate date1 = LocalDate.of(2022, 4, 16);
        LocalDate date2 = LocalDate.of(2020, 2, 25);
        List<Question> questions1 = new ArrayList<>();
        List<Question> questions2 = new ArrayList<>();
        Quiz quiz1 = new Quiz("quiz1", 0, CategoryEnum.MATEMATYKA, LanguageEnum.PL, date1, questions1 );
        Quiz quiz2 = new Quiz("quiz2", 0, CategoryEnum.HISTORIA, LanguageEnum.EN, date2, questions2 );

        List<Answer> answers1 = new ArrayList<>();
        List<Answer> answers2 = new ArrayList<>();
        Question question1 = new Question("question1", QuestionTypeEnum.OPEN, quiz1, answers1);
        Question question2 = new Question("question2", QuestionTypeEnum.OPEN, quiz1, answers2);
        //Question question3 = new Question("question3", QuestionTypeEnum.OPEN, quiz2, answers2);
        //Question question4 = new Question("question4", QuestionTypeEnum.OPEN, quiz2, answers2);

        Answer answer1 = new Answer("True1", true, question1);
        Answer answer2 = new Answer("False1", false, question1);
        Answer answer3 = new Answer("False2", false, question1);
        Answer answer4 = new Answer("True2", true, question2);
        Answer answer5 = new Answer("False3", false, question2);
        Answer answer6 = new Answer("False4", false, question2);

        answers1.add(answer1);
        answers1.add(answer2);
        answers1.add(answer3);
        answers2.add(answer4);
        answers2.add(answer5);
        answers2.add(answer6);

        question1.setAnswerList(answers1);
        question2.setAnswerList(answers2);
        //question3.setAnswerList(answers2);
        //question4.setAnswerList(answers2);

        questions1.add(question1);
        questions1.add(question2);
        //questions2.add(question3);
        //questions2.add(question4);

        quiz1.setQuestionList(questions1);
        quiz2.setQuestionList(questions2);

        answerRepository.save(answer1);
        answerRepository.save(answer2);
        answerRepository.save(answer3);
        answerRepository.save(answer4);
        answerRepository.save(answer5);
        answerRepository.save(answer6);

        questionRepository.save(question1);
        questionRepository.save(question2);
        //questionRepository.save(question3);
        //questionRepository.save(question4);

        quizRepository.save(quiz1);
        quizRepository.save(quiz2);

    }

}
