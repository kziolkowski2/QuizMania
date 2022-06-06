package com.Quizmania.Quizmania.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import net.bytebuddy.asm.Advice;

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
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "quiz_id") //moze byc problem z wielka litera w nazwie
    private Quiz parentQuiz;
    @JsonIgnore
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Answer> answerList = new ArrayList<>();

    public Question() {
        this.content = "";
        this.questionType = QuestionTypeEnum.CLOSED;
        List<Answer> tempAnswerList = new ArrayList<>();
        this.answerList = tempAnswerList;
    }

    public Question(String content, QuestionTypeEnum questionType, List<Answer> answerList) {
        this.content = content;
        this.questionType = questionType;
        this.answerList = answerList;
    }

    public Question(String content, QuestionTypeEnum questionType, Quiz parentQuiz, List<Answer> answerList) {
        this.content = content;
        this.questionType = questionType;
        this.parentQuiz = parentQuiz;
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

    public Quiz getParentQuiz() {
        return parentQuiz;
    }

    public void setParentQuiz(Quiz parentQuiz) {
        this.parentQuiz = parentQuiz;
    }

    public List<Answer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<Answer> answerList) {
        this.answerList = answerList;
    }

    public void addAnswerToList(Answer answer){
        this.answerList.add(answer);
    }
    public void addAnswerToListString(String content,String isCorrect){
        this.answerList.add(new Answer(content,isCorrect));
    }


}
