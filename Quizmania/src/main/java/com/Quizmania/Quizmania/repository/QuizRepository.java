package com.Quizmania.Quizmania.repository;

import com.Quizmania.Quizmania.domain.Answer;
import com.Quizmania.Quizmania.domain.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
}
