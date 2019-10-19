package com.bretthirschberger.project2;

public abstract class Question {
    private String[] options;
    private String question;

    public Question(String question, String[] options) {
        this.question = question;
        this.options = options;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }
}
