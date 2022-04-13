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
    private int score = 0;
    private int timeLimit;
    @NotNull
    private CategoryEnum category; // kategoria pyta�
    @NotNull
    private LanguageEnum language; // j�zyk pyta� i odpowiedzi w zestawie
    @Nullable
    private int popularity = 0;  //popularno�� (ilo�� wy�wietle�)
    private LocalDate date; //data dodania
    @OneToMany
    @JsonIgnore
    private List<Question> questionList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Nullable
    public String getName() {
        return name;
    }

    public void setName(@Nullable String name) {
        this.name = name;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
    }

    public LanguageEnum getLanguage() {
        return language;
    }

    public void setLanguage(LanguageEnum language) {
        this.language = language;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    public Quiz(@Nullable String name, int timeLimit, CategoryEnum category, LanguageEnum language, LocalDate date, List<Question> questionList) {
        this.name = name;
        this.timeLimit = timeLimit;
        this.category = category;
        this.language = language;
        this.date = date;
        this.questionList = questionList;
    }

    public Quiz() {
    }
    public Quiz(@Nullable String name) {
        this.name = name;
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
