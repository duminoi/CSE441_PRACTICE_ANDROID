// Question.java
package com.example.blxproject;

import java.util.Map;

public class Question {
    private String id;
    private String question;
    private Map<String, String> option;
    private Map<String, String> image;
    private String answer;
    private String suggest;

    // Getters và setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Map<String, String> getOption() {
        return option;
    }

    public void setOption(Map<String, String> option) {
        this.option = option;
    }

    public Map<String, String> getImage() {
        return image;
    }

    public void setImage(Map<String, String> image) {
        this.image = image;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getSuggest() {
        return suggest;
    }

    public void setSuggest(String suggest) {
        this.suggest = suggest;
    }
}



//package com.example.blxproject;
//
//import java.util.Map;
//public class Question {
//    private String question; // Câu hỏi
//    private String[] options; // Các đáp án
//    private int answerIndex; // Chỉ số đáp án đúng
//
//    public Question(String question, String[] options, int answerIndex) {
//        this.question = question;
//        this.options = options;
//        this.answerIndex = answerIndex;
//    }
//
//    public String getQuestion() {
//        return question;
//    }
//
//    public String[] getOptions() {
//        return options;
//    }
//
//    public int getAnswerIndex() {
//        return answerIndex;
//    }
//}
//
//
//

