package com.example.blxproject;

import java.util.Map;
public class Question {
    private String question; // Câu hỏi
    private String[] options; // Các đáp án
    private int answerIndex; // Chỉ số đáp án đúng

    public Question(String question, String[] options, int answerIndex) {
        this.question = question;
        this.options = options;
        this.answerIndex = answerIndex;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public int getAnswerIndex() {
        return answerIndex;
    }
}



