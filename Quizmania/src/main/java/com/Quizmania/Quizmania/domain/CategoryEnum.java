package com.Quizmania.Quizmania.domain;

public enum CategoryEnum {
    MATEMATYKA("Matematyka"),
    PRZYRODA("Przyroda"),
    TECHNOLOGIA("Technologia"),
    JEZYKOWE("Językowe"),
    HISTORIA("Historia");

    private final String text;

    CategoryEnum(final String text) {
        this.text = text;
    }

    public String getDisplayValue(){
        return text;
    }
}
