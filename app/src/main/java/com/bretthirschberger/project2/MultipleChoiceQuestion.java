package com.bretthirschberger.project2;

public class MultipleChoiceQuestion {
    private String question;
    private String[] options;
    private int correctAnswer;

    public MultipleChoiceQuestion(String question, String[] options, int correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String[] getOptions() {
        return options;
    }

    public String getQuestion() {
        return question;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }
}
