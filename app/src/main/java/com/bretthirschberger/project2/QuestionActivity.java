package com.bretthirschberger.project2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class QuestionActivity extends AppCompatActivity implements
        MultipleChoiceFragment.OnOptionSelectedListener, MultipleAnswerFragment.OnOptionSelectedListener {

    private Question[] mQuestions;
    private FragmentManager mFragmentManager;
    private int mQuestionNumber;
    private int mCorrectAnswers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        if (savedInstanceState != null) {
            mQuestionNumber = savedInstanceState.getInt("questionNumber");
            mCorrectAnswers = savedInstanceState.getInt("correctAnswers");
        } else {
            //creates dialogue window
            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.activity_rules);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
        }
        mFragmentManager = getSupportFragmentManager();
        mQuestions = new Question[]{
                new MultipleChoiceQuestion(getString(R.string.q1),
                        new String[]{
                                getString(R.string.q1a1),
                                getString(R.string.q1a2),
                                getString(R.string.q1a3),
                                getString(R.string.q1a4),
                        }, 2),
                new MultipleAnswerQuestion(getString(R.string.question2),
                        new String[]{
                                getString(R.string.q2a1),
                                getString(R.string.q2a2),
                                getString(R.string.q2a3),
                                getString(R.string.q2a4),
                                getString(R.string.q2a5),
                        }, new int[]{2, 4}),
                new MultipleChoiceQuestion(getString(R.string.question3),
                        new String[]{
                                getString(R.string.q3a1),
                                getString(R.string.q3a2),
                                getString(R.string.q3a3),
                                getString(R.string.q3a4),
                        }, 4),
                new MultipleAnswerQuestion(getString(R.string.question4),
                        new String[]{
                                getString(R.string.q4a1),
                                getString(R.string.q4a2),
                                getString(R.string.q4a3),
                                getString(R.string.q4a4),
                                getString(R.string.q4a5),
                        }, new int[]{1, 5}),
                new MultipleChoiceQuestion(getString(R.string.question5),
                        new String[]{
                                getString(R.string.q5a1),
                                getString(R.string.q5a2),
                                getString(R.string.q5a3),
                                getString(R.string.q5a4),
                        }, 3)
        };
        displayQuestion(mQuestions[mQuestionNumber]);

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("questionNumber", mQuestionNumber);
        outState.getInt("correctAnswers", mCorrectAnswers);
    }

    private void displayQuestion(Question question) {
        MultipleAnswerFragment mMultipleAnswerFragment;
        MultipleChoiceFragment mMultipleChoiceFragment;
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        if (question instanceof MultipleChoiceQuestion) {
            mMultipleChoiceFragment = MultipleChoiceFragment.newInstance((MultipleChoiceQuestion) question);
            fragmentTransaction.replace(R.id.question_layout, mMultipleChoiceFragment);
        } else if (question instanceof MultipleAnswerQuestion) {
            mMultipleAnswerFragment = MultipleAnswerFragment.newInstance((MultipleAnswerQuestion) question);
            fragmentTransaction.replace(R.id.question_layout, mMultipleAnswerFragment);
        }
        fragmentTransaction.commit();
    }

    @Override
    public void goToNext(boolean isCorrect) {
        if (isCorrect) {
            Toast.makeText(getApplicationContext(), getString(R.string.correct), Toast.LENGTH_SHORT).show();
            mCorrectAnswers++;
        } else {
            Toast.makeText(getApplicationContext(), getString(R.string.incorrect), Toast.LENGTH_SHORT).show();
        }
        if (mQuestionNumber + 1 == mQuestions.length) {
            //writes scores to a file
            File scores = new File(getFilesDir().getAbsolutePath() + "/scores.txt");
            try (FileWriter writer = new FileWriter(scores, true)) {
                writer.write(getName() + ": " + mCorrectAnswers + "/" + mQuestions.length + "\n");
                startActivity(new Intent(this, ScoresActivity.class).putExtra("email", getIntent().getStringExtra("email")));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        displayQuestion(mQuestions[++mQuestionNumber]);
    }

    private String getName() {
        String email = getIntent().getStringExtra("email");

        try (Scanner reader = new Scanner(new File(getFilesDir().getAbsolutePath() + "/users.txt"))) {
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] userInfo = line.split(",");
                Log.i("File Line", line);
                if (userInfo[0].equals(email)) {
                    return userInfo[1];
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return "ERROR";
    }

}
