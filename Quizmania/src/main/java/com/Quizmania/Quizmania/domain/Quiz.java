package com.Quizmania.Quizmania.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Scanner;

@Entity
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private boolean mode;
    private int score;
    private int timeLimit;
    private boolean isTimed;
    private QuestionSet questionSet;

    public Quiz(boolean mode, QuestionSet questionSet) {
        this.mode = mode;
        this.timeLimit = 0;
        this.questionSet = questionSet;
        isTimed = false;
        Globals.quizDB.add(this);
    }

    public Quiz(int timeLimit, QuestionSet questionSet) {
        mode = false;
        this.timeLimit = timeLimit;
        this.questionSet = questionSet;
        if (timeLimit > 0)
            isTimed = true;
        Globals.quizDB.add(this);
    }
    
    public Quiz() {
    	mode = false;
    	timeLimit = 0;
    	questionSet = new QuestionSet();
    	isTimed = false;
    	Globals.quizDB.add(this);
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
        if(timeLimit > 0)
            isTimed = true;
        else
            isTimed = false;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public boolean getIsTimed() {
        return isTimed;
    }

    public boolean getMode() {
        return mode;
    }
    
    public QuestionSet getQuestionSet() {
    	return questionSet;
    }

    public void toggleMode() {
        mode = !mode;
        timeLimit = 0;
        isTimed = false;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void grantPoint() {
        score++;
    }

    public void playQuiz() {
        Scanner scanner = new Scanner(System.in);
        for(Question question : questionSet.questionList) {
            question.showQuestion();
            String scannerAnswer = scanner.nextLine();
            if (question.correctAnswerList.contains(scannerAnswer))
                grantPoint();
            System.out.println("Score: " + score + "\n");
        }

        float accuracy = score / questionSet.questionList.size(); //ewentualna aktualizacja i wy�wietlenie trafno�ci w procentach
        if(accuracy > questionSet.accuracy)
            questionSet.setAccuracy(accuracy);
        System.out.println("Accuracy: " + accuracy * 100 + "% | " + "Best accuracy: "
                + questionSet.accuracy * 100 + "%" + "\n");
        questionSet.setPopularity(questionSet.getPopularity() + 1); // zwi�kszenie ilo�ci wy�wietle� o jeden
        System.out.println("Rate this flashcard set from 1 to 10 \n"); //ocena quizu
        int rating = scanner.nextInt();
        if (rating > 10)
            rating = 10;
        if (rating < 1)
            rating = 1;
        questionSet.setRating(rating);
        scanner.close();
    }

    public void playFlashcardSet() {
        Scanner scanner = new Scanner(System.in);
        for(Question question : questionSet.questionList) {
            question.showFlashcard();
            scanner.nextLine();
            question.showFlashcardAnswer();
            scanner.nextLine();
        }
        float accuracy = score / questionSet.questionList.size(); //ewentualna aktualizacja i wy�wietlenie trafno�ci w procentach
        if(accuracy > questionSet.accuracy)
            questionSet.setAccuracy(accuracy);
        System.out.println("Accuracy: " + accuracy * 100 + "% | " + "Best accuracy: "
                + questionSet.accuracy * 100 + "%" + "\n");
        questionSet.setPopularity(questionSet.getPopularity() + 1); // zwi�kszenie ilo�ci wy�wietle� o jeden
        System.out.println("Rate this flashcard set from 1 to 10 \n"); //ocena quizu
        int rating = scanner.nextInt();
        if (rating > 10)
            rating = 10;
        if (rating < 1)
            rating = 1;
        questionSet.setRating(rating);
        scanner.close();
    }
}
