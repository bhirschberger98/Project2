package com.bretthirschberger.project2;

public class MultipleAnswerQuestion extends Question {
    private int[] correctAnswers;

    public MultipleAnswerQuestion(String question, String[] options, int[] correctAnswers){
        super(question, options);
        this.correctAnswers = correctAnswers;
    }

    public int[] getCorrectAnswers(){
        return correctAnswers;
    }
}
