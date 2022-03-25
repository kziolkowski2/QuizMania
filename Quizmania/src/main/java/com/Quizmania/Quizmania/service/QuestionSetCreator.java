package com.Quizmania.Quizmania.service;

import com.Quizmania.Quizmania.domain.Answer;
import com.Quizmania.Quizmania.domain.Question;
import com.Quizmania.Quizmania.domain.QuestionSet;

import java.util.Scanner;

public class QuestionSetCreator {
    QuestionSetCreator(){}
    public QuestionSet createQuestionSet() {
        System.out.println("QUESTION SET CREATOR\n");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter question set name: \n");
        String name = scanner.nextLine();
        System.out.println("Enter question set category: \n");
        String category = scanner.nextLine();
        System.out.println("Enter question set language: \n");
        String language = scanner.nextLine();
        QuestionSet questionSet = new QuestionSet(name, category, language);
        while (true) {
            System.out.println("Enter questions, type \"quit\" to finish");
            String question = scanner.nextLine();
            if (question.equals("quit")) {
                break;
            } else {
                questionSet.addQuestion(new Question(question, false));
                while (true) {
                    System.out.println("Enter wrong answers, type \"next\" to continue");
                    String answer = scanner.nextLine();
                    if (answer.equals("next")) {
                        break;
                    } else {
                        questionSet.getQuestionList().get(questionSet.getQuestionList().size() - 1).addAnswer(new Answer(answer));
                    }
                }
                System.out.println("Enter correct answer, type \"next\" to continue");
                String answer = scanner.nextLine();
                questionSet.getQuestionList().get(questionSet.getQuestionList().size() - 1).addAnswer(new Answer(answer, true));
            }
        }
        scanner.close();
        return questionSet;
    }

}