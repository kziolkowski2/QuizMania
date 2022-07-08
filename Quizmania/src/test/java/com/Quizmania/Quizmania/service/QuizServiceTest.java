package com.Quizmania.Quizmania.service;

import com.Quizmania.Quizmania.domain.Question;
import com.Quizmania.Quizmania.domain.Quiz;
import com.Quizmania.Quizmania.repository.AnswerRepository;
import com.Quizmania.Quizmania.repository.QuestionRepository;
import com.Quizmania.Quizmania.repository.QuizRepository;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class QuizServiceTest {
    
    @Mock
    QuizRepository quizRepository;
    AnswerRepository answerRepository;
    QuestionRepository questionRepository;

    @InjectMocks
    QuizService quizService;

    Question question = new Question();
    Quiz quiz = new Quiz();

    @Test
    public void testSaveQuiz() {
        quizService.save(quiz);
        verify(quizRepository, times(1)).save(quiz);
    }
    @Test
    public void testSaveQuestion() {
        quizService.save(quiz);
        verify(quizRepository, times(1)).save(quiz);
    }

}
