package com.Quizmania.Quizmania.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String content;
    @NotNull
    private QuestionTypeEnum questionType;
    @NotNull
    private CategoryEnum category;
    @NotNull
    private LanguageEnum language;
    @ManyToOne
    @JoinColumn(name = "questionSet_id") //moze byc problem z wielka litera w nazwie
    private QuestionSet parentQuestionSet;
    @OneToMany
    private List<Answer> answerList;

    public Question(String content, QuestionTypeEnum questionType, CategoryEnum category, LanguageEnum language, QuestionSet parentQuestionSet, List<Answer> answerList) {
        this.content = content;
        this.questionType = questionType;
        this.category = category;
        this.language = language;
        this.parentQuestionSet = parentQuestionSet;
        this.answerList = answerList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public QuestionTypeEnum getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionTypeEnum questionType) {
        this.questionType = questionType;
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

    public QuestionSet getParentQuestionSet() {
        return parentQuestionSet;
    }

    public void setParentQuestionSet(QuestionSet parentQuestionSet) {
        this.parentQuestionSet = parentQuestionSet;
    }

    public List<Answer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<Answer> answerList) {
        this.answerList = answerList;
    }

    @Override
    public String toString() {
        return "Question{" +
                "content='" + content + '\'' +
                ", parentQuestionSet=" + parentQuestionSet +
                ", answerList=" + answerList +
                '}';
    }
//    public void shuffleAnswerList() {
//        Collections.shuffle(answerList);
//    }

//    public void showQuestion() {
//        String output = "////////////////////\n" + content + "\n";
//        shuffleAnswerList();
//        int i = 0;
//        for(Answer answer : answerList) {
//            output += ++i + ") " + answer.toString() + "\n";
//        }
//        System.out.println(output);
//    }
}
