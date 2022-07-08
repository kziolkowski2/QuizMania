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

    public Quiz findByName(String name){
        return quizRepository.findByName(name);
    }
    public Optional<Quiz> find(Long id){
        return quizRepository.findById(id);
    }
    public Question findQuestion(Long id, int questionIndex){
        return quizRepository.findById(id).get().getQuestionList().get(questionIndex);
    }

    public Question save(Question question){
        return questionRepository.save(question);
    }
    public void setQuestionListToQuiz(Quiz quiz,ArrayList<Question> list){
        quiz.setQuestionList(list);
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
    
    public Quiz saveWithList(Quiz quiz,List<Question> questionList) {
        quiz.setQuestionList(questionList);
        //quiz.setCreatedBy(user);
        return quizRepository.save(quiz);
    }
    public Quiz save(Quiz quiz) {
        //quiz.setCreatedBy(user);
        return quizRepository.save(quiz);
    }
    public void deleteQuestionById(Long id){
        questionRepository.deleteById(id);
    }
    public void deleteAnswerById(Long id){
        answerRepository.deleteById(id);
    }
    public void initialize() {
        LocalDate date1 = LocalDate.of(2022, 4, 16);
        LocalDate date2 = LocalDate.of(2020, 2, 25);
        List<Question> questions1 = new ArrayList<>();
        List<Question> questions2 = new ArrayList<>();
        Quiz quiz1 = new Quiz("Przykładowy quiz", 0.75f, CategoryEnum.MATEMATYKA, LanguageEnum.PL, date1, questions1 );
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
        List<Answer> answers4 = new ArrayList<>();

        Question question1 = new Question("Ile to 1+1?", QuestionTypeEnum.SINGLE, 1,  quiz1, answers1);
        Question question2 = new Question("Które miasto jest stolicą Boliwii? (Wybierz wszystkie poprawne odpowiedzi)", QuestionTypeEnum.MULTIPLE, 2, quiz1, answers2);
        Question question3 = new Question("Symbol pierwiastka sodu to?", QuestionTypeEnum.OPEN, 1, quiz1, answers3);
        Question question4 = new Question("Określ prawdziwość poniższych stwierdzeń", QuestionTypeEnum.TF, 2, quiz1, answers4);


        //Question question3 = new Question("question3", QuestionTypeEnum.OPEN, quiz2, answers2);
        //Question question4 = new Question("question4", QuestionTypeEnum.OPEN, quiz2, answers2);

        Answer answer1 = new Answer("2", true, question1);
        Answer answer2 = new Answer("0", false, question1);
        Answer answer3 = new Answer("3", false, question1);

        Answer answer4 = new Answer("Sucre", true, question2);
        Answer answer5 = new Answer("La Paz", true, question2);
        Answer answer6 = new Answer("Warszawa", false, question2);

        Answer answer7 = new Answer("Na", true, question3);
        
        Answer answer8 = new Answer("Czy delfin to ssak?", true, question4);
        Answer answer9 = new Answer("Czy Burj Khalifa to najwyższy budynek świata", true, question4);
        Answer answer10 = new Answer("Czy Paryż to stolica Czech?", false, question4);

        answers1.add(answer1);
        answers1.add(answer2);
        answers1.add(answer3);

        answers2.add(answer4);
        answers2.add(answer5);
        answers2.add(answer6);

        answers3.add(answer7);
        
        answers4.add(answer8);
        answers4.add(answer9);
        answers4.add(answer10);

        question1.setAnswerList(answers1);
        question2.setAnswerList(answers2);
        question3.setAnswerList(answers3);
        question4.setAnswerList(answers4);

        questions1.add(question1);
        questions1.add(question2);
        questions1.add(question3);
        questions1.add(question4);

        quiz1.setQuestionList(questions1);
        quiz2.setQuestionList(questions2);
        

        answerRepository.save(answer1);
        answerRepository.save(answer2);
        answerRepository.save(answer3);
        answerRepository.save(answer4);
        answerRepository.save(answer5);
        answerRepository.save(answer6);
        answerRepository.save(answer7);
        answerRepository.save(answer8);
        answerRepository.save(answer9);
        answerRepository.save(answer10);


        questionRepository.save(question1);
        questionRepository.save(question2);
        questionRepository.save(question3);
        questionRepository.save(question4);

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
