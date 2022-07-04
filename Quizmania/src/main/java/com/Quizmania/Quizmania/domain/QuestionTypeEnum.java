package com.Quizmania.Quizmania.domain;

public enum QuestionTypeEnum {
    OPEN("open"),
    SINGLE("single"),
    MULTIPLE("multiple"),
    TF("true/false");

    private final String text;

    QuestionTypeEnum(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}