package com.Quizmania.Quizmania.domain;

public enum LanguageEnum {
    PL("Polski"),
    EN("Angielski");

    private final String text;

    LanguageEnum(final String text) {
        this.text = text;
    }

    public String getDisplayValue(){
        return text;
    }
}
