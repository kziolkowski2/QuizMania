package com.Quizmania.Quizmania.repository;

import com.Quizmania.Quizmania.domain.Answer;
import com.Quizmania.Quizmania.domain.QuestionSet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionSetRepository extends CrudRepository<QuestionSet,Long> {
}
