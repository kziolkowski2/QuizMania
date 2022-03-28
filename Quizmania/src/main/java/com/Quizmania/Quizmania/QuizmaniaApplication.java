package com.Quizmania.Quizmania;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class QuizmaniaApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizmaniaApplication.class, args);
	}

	@Bean
	public CommandLineRunner setUpApp(@Autowired QuizService quizService) {
		return (args) -> quizService.initialize();
	}

}
