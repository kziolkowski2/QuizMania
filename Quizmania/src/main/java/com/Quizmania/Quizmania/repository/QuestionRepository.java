package com.Quizmania.Quizmania.repository;

import com.Quizmania.Quizmania.domain.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Long> {
}
