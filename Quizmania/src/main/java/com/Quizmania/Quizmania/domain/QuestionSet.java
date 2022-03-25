package com.Quizmania.Quizmania.domain;

import com.sun.istack.NotNull;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
public class QuestionSet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Nullable
    private String name; // nazwa zestawu
    @NotNull
    private CategoryEnum category; // kategoria pyta�
    @NotNull
    private LanguageEnum language; // j�zyk pyta� i odpowiedzi w zestawie
    @Nullable
    private int popularity = 0;  //popularno�� (ilo�� wy�wietle�)
    @Nullable
    private int rating = 0;  //ocena zestawu
    @Nullable
    private float accuracy = 0; //najwi�ksza dotychczasowa trafno�� w procentach
    private LocalDate date; //data dodania
    @OneToMany
    private List<Question> questionList;

//kontruktory gettery i settery jeszcze do ogarniecia


    public QuestionSet(@Nullable String name, CategoryEnum category, LanguageEnum language, LocalDate date, List<Question> questionList) {
        this.name = name;
        this.category = category;
        this.language = language;
        this.date = date;
        this.questionList = questionList;
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


    public void setCategory(CategoryEnum category) {
        this.category = category;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public float getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(float accuracy) {
        this.accuracy = accuracy;
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

    public String getCategory() { return category.toString(); }
    public String getLanguage() { return language.toString(); }

    public void addQuestion(Question question) {
        questionList.add(question);
        question.setParentQuestionSet(this);
    }

    public void removeQuestion(int index) {
        questionList.remove(index);
    }
}
