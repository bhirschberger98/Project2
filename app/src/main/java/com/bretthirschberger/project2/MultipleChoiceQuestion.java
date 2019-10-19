package com.bretthirschberger.project2;

public class MultipleChoiceQuestion extends Question {
    private int correctAnswer;

    public MultipleChoiceQuestion(String question, String[] options, int correctAnswer) {
        super(question, options);
        this.correctAnswer = correctAnswer;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }
}
