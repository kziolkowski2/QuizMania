package com.Quizmania.Quizmania.repository;

import com.Quizmania.Quizmania.domain.Answer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends PagingAndSortingRepository<Answer,Long> {
}
