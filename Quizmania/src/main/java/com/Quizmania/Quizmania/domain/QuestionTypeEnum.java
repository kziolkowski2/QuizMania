package com.Quizmania.Quizmania.domain;

public enum QuestionTypeEnum {
    OPEN("open"),
    CLOSED("closed");

    private final String text;

    QuestionTypeEnum(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
