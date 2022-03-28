package com.Quizmania.Quizmania.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
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
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "questionSet_id", referencedColumnName = "id")
    private QuestionSet questionSet;

    public Quiz() {
    }

    public Quiz(boolean mode, int timeLimit, boolean isTimed, QuestionSet questionSet) {
        this.mode = mode;
        this.score = 0;
        this.timeLimit = timeLimit;
        this.isTimed = isTimed;
        this.questionSet = questionSet;
    }
    public void grantPoint() {
        score++;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
        if(timeLimit > 0)
            isTimed = true;
        else
            isTimed = false;
    }

    public void toggleMode() {
        mode = !mode;
        timeLimit = 0;
        isTimed = false;
    }
    public void playQuiz(){
        long startTime = System.currentTimeMillis();

    }


//    public void playQuiz() {
//        Scanner scanner = new Scanner(System.in);
//        for(Question question : questionSet.questionList) {
//            question.showQuestion();
//            String scannerAnswer = scanner.nextLine();
//            if (question.correctAnswerList.contains(scannerAnswer))
//                grantPoint();
//            System.out.println("Score: " + score + "\n");
//        }
//
//        float accuracy = score / questionSet.questionList.size(); //ewentualna aktualizacja i wy�wietlenie trafno�ci w procentach
//        if(accuracy > questionSet.accuracy)
//            questionSet.setAccuracy(accuracy);
//        System.out.println("Accuracy: " + accuracy * 100 + "% | " + "Best accuracy: "
//                + questionSet.accuracy * 100 + "%" + "\n");
//        questionSet.setPopularity(questionSet.getPopularity() + 1); // zwi�kszenie ilo�ci wy�wietle� o jeden
//        System.out.println("Rate this flashcard set from 1 to 10 \n"); //ocena quizu
//        int rating = scanner.nextInt();
//        if (rating > 10)
//            rating = 10;
//        if (rating < 1)
//            rating = 1;
//        questionSet.setRating(rating);
//        scanner.close();
//    }
}
