// QuestionResult.java
package com.example.gplxb2;

import java.io.Serializable;

public class QuestionResult implements Serializable {
    private String questionText;
    private boolean isCorrect;

    public QuestionResult(String questionText, boolean isCorrect) {
        this.questionText = questionText;
        this.isCorrect = isCorrect;
    }

    public String getQuestionText() {
        return questionText;
    }

    public boolean isCorrect() {
        return isCorrect;
    }
}
