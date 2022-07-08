package com.Quizmania.Quizmania.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Entity
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Nullable
    private String name; // nazwa zestawu
    @Nullable
    private float timeLimit;
    @NotNull
    private int maxPoints;
    @NotNull
    private CategoryEnum category;
    @NotNull
    private LanguageEnum language;
    @Nullable
    private int popularity = 0;
    private LocalDate date;
    @JsonIgnore
    @OneToMany
    @JoinColumn (name = "quiz_id")
    private List<Question> questionList = new ArrayList<>();

    public Quiz() {
        this.name = "";
        this.timeLimit = 0;
        this.category = CategoryEnum.MATEMATYKA;
        this.language = LanguageEnum.PL;
        this.popularity = 0;
        this.date = LocalDate.now();
    }
    public Quiz(@Nullable String name, float timeLimit, CategoryEnum category, LanguageEnum language, LocalDate date, List<Question> questionList) {
        this.name = name;
        this.timeLimit = timeLimit;
        this.category = category;
        this.language = language;
        this.date = date;
        this.questionList = questionList;
        this.maxPoints = questionList.stream().mapToInt(q -> q.getPoints()).sum();
    }
    public Quiz(String name) {
        this.name = name;
        this.timeLimit = 0;
        this.category = CategoryEnum.MATEMATYKA;
        this.language = LanguageEnum.PL;
        this.popularity = 0;
        this.date = LocalDate.now();
    }


    public void addQuestionToList(Question question){
        this.questionList.add(question);
    }

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

    public int getMaxPoints() { return maxPoints;}

    public void setMaxPoints(int maxPoints) { this.maxPoints = maxPoints; }

    public float getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(float timeLimit) {
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

    public void incrementPopularity() { this.popularity += 1;}

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
        this.maxPoints = questionList.stream().mapToInt(q -> q.getPoints()).sum();
    }



}