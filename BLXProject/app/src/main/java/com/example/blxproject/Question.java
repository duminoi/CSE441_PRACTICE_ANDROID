package com.example.blxproject;

import java.io.Serializable;

// Lớp đại diện cho câu hỏi
public class Question implements Serializable {
    private String id;
    private String question;
    private Option option;
    private Image image;
    private String answer;
    private String suggest;

    // Getters và setters cho các thuộc tính của Question
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

    public Option getOption() {
        return option;
    }

    public void setOption(Option option) {
        this.option = option;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
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

    // Lớp đại diện cho các tùy chọn đáp án
    public static class Option implements Serializable { // Đảm bảo Option cũng implements Serializable
        private String a;
        private String b;
        private String c;
        private String d;
        private String e;

        // Getters và setters cho Option
        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        public String getB() {
            return b;
        }

        public void setB(String b) {
            this.b = b;
        }

        public String getC() {
            return c;
        }

        public void setC(String c) {
            this.c = c;
        }

        public String getD() {
            return d;
        }

        public void setD(String d) {
            this.d = d;
        }

        public String getE() {
            return e;
        }

        public void setE(String e) {
            this.e = e;
        }
    }

    // Lớp đại diện cho các hình ảnh
    public static class Image implements Serializable { // Implement Serializable
        private String img1;
        private String img2;
        private String img3;
        private String img4;

        // Getters và setters cho Image
        public String getImg1() {
            return img1;
        }

        public void setImg1(String img1) {
            this.img1 = img1;
        }

        public String getImg2() {
            return img2;
        }

        public void setImg2(String img2) {
            this.img2 = img2;
        }

        public String getImg3() {
            return img3;
        }

        public void setImg3(String img3) {
            this.img3 = img3;
        }

        public String getImg4() {
            return img4;
        }

        public void setImg4(String img4) {
            this.img4 = img4;
        }
    }
}
