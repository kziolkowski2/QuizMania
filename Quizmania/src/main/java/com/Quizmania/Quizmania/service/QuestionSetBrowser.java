package com.Quizmania.Quizmania.service;

import com.Quizmania.Quizmania.domain.QuestionSet;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

public class QuestionSetBrowser {
    private List<QuestionSet> questionSetList;

    public QuestionSetBrowser(){}
    public void addQuestionSet(QuestionSet questionSet) {
        questionSetList.add(questionSet);
    }
    public QuestionSet chooseQuestionSet(String name){
        for (QuestionSet questionSet : questionSetList)
            if(questionSet.getName().equals(name))
                return questionSet;
        return null;

    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (QuestionSet questionSet : questionSetList)
            result.append("Name: ").append(questionSet.getName()).append(" | ")
                    .append("Category: ").append(questionSet.getCategory()).append(" | ")
                    .append("Language: ").append(questionSet.getLanguage()).append(" | ")
                    .append("Popularity: ").append(questionSet.getPopularity()).append(" | ")
                    .append("Accuracy: ").append(questionSet.getAccuracy()).append("\n");
        return result.toString();


    }
}