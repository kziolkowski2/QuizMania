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
        Quiz quiz1 = new Quiz("Matura z matematyki 2022", 0, CategoryEnum.MATEMATYKA, LanguageEnum.PL, date1, questions1 );
        Quiz quiz2 = new Quiz("quiz 2", 0, CategoryEnum.HISTORIA, LanguageEnum.EN, date2, questions2 );
        Quiz quiz3 = new Quiz("quiz 3");
        Quiz quiz4 = new Quiz("quiz 4");
        Quiz quiz5 = new Quiz("quiz 5");
        Quiz quiz6 = new Quiz("quiz 6");
        Quiz quiz7 = new Quiz("quiz 7");
        Quiz quiz8 = new Quiz("quiz 8");
        Quiz quiz9 = new Quiz("quiz 9");
        Quiz quiz10 = new Quiz("quiz 10");
        Quiz quiz11 = new Quiz("quiz 11");
        Quiz quiz12 = new Quiz("quiz 12");
        Quiz quiz13 = new Quiz("quiz 13");
        Quiz quiz14 = new Quiz("quiz 14");
        Quiz quiz15 = new Quiz("quiz 15");


        List<Answer> answers1 = new ArrayList<>();
        List<Answer> answers2 = new ArrayList<>();
        List<Answer> answers3 = new ArrayList<>();
        Question question1 = new Question("Ile to 1+1?", QuestionTypeEnum.CLOSED, quiz1, answers1);
        Question question2 = new Question("Ile to 4/2?", QuestionTypeEnum.CLOSED, quiz1, answers2);
        Question question3 = new Question("Ile to 3-3?", QuestionTypeEnum.OPEN, quiz1, answers3);

        //Question question3 = new Question("question3", QuestionTypeEnum.OPEN, quiz2, answers2);
        //Question question4 = new Question("question4", QuestionTypeEnum.OPEN, quiz2, answers2);

        Answer answer1 = new Answer("2", true, question1);
        Answer answer2 = new Answer("0", false, question1);
        Answer answer3 = new Answer("3", false, question1);

        Answer answer4 = new Answer("2", true, question2);
        Answer answer5 = new Answer("8", false, question2);
        Answer answer6 = new Answer("1", false, question2);

        Answer answer7 = new Answer("0", true, question3);
        //Answer answer8 = new Answer("1", false, question2);
        //Answer answer9 = new Answer("3", false, question2);

        answers1.add(answer1);
        answers1.add(answer2);
        answers1.add(answer3);

        answers2.add(answer4);
        answers2.add(answer5);
        answers2.add(answer6);

        answers3.add(answer7);
        //answers3.add(answer8);
        //answers3.add(answer9);

        question1.setAnswerList(answers1);
        question2.setAnswerList(answers2);
        question3.setAnswerList(answers3);
        //question4.setAnswerList(answers2);

        questions1.add(question1);
        questions1.add(question2);
        questions1.add(question3);
        //questions2.add(question4);

        quiz1.setQuestionList(questions1);
        quiz2.setQuestionList(questions2);


        answerRepository.save(answer1);
        answerRepository.save(answer2);
        answerRepository.save(answer3);
        answerRepository.save(answer4);
        answerRepository.save(answer5);
        answerRepository.save(answer6);
        answerRepository.save(answer7);
        //answerRepository.save(answer8);
        //answerRepository.save(answer9);

        questionRepository.save(question1);
        questionRepository.save(question2);
        questionRepository.save(question3);
        //questionRepository.save(question3);
        //questionRepository.save(question4);

        quizRepository.save(quiz1);
        quizRepository.save(quiz2);
        quizRepository.save(quiz3);
        quizRepository.save(quiz4);
        quizRepository.save(quiz5);
        quizRepository.save(quiz6);
        quizRepository.save(quiz7);
        quizRepository.save(quiz8);
        quizRepository.save(quiz9);
        quizRepository.save(quiz10);
        quizRepository.save(quiz11);
        quizRepository.save(quiz12);
        quizRepository.save(quiz13);
        quizRepository.save(quiz14);
        quizRepository.save(quiz15);

    }

}
