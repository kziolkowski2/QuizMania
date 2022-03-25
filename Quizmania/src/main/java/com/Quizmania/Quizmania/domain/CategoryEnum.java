package com.Quizmania.Quizmania.domain;

public enum CategoryEnum {
    MATEMATYKA("Matematyka"),
    HISTORIA("Historia");

    private final String text;

    CategoryEnum(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
