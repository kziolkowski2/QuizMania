package com.Quizmania.Quizmania.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Nullable
    private String name; // nazwa zestawu
    private boolean isActive;//on/off
    private int score;
    private int timeLimit;
    @NotNull
    private CategoryEnum category; // kategoria pyta�
    @NotNull
    private LanguageEnum language; // j�zyk pyta� i odpowiedzi w zestawie
    @Nullable
    private int popularity = 0;  //popularno�� (ilo�� wy�wietle�)
    private LocalDate date; //data dodania
    @OneToMany
    private List<Question> questionList;

    public Quiz() {
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
