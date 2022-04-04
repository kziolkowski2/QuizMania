package com.Quizmania.Quizmania.repository;

import com.Quizmania.Quizmania.domain.Answer;
import com.Quizmania.Quizmania.domain.Quiz;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends PagingAndSortingRepository<Quiz, Long> {
}
