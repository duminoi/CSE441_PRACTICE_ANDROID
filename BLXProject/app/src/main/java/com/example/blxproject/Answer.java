package com.example.blxproject;

public class Answer {
    private String question;
    private boolean isCorrect;

    public Answer(String question, boolean isCorrect) {
        this.question = question;
        this.isCorrect = isCorrect;
    }

    public String getQuestion() {
        return question;
    }

    public boolean isCorrect() {
        return isCorrect;
    }
}
