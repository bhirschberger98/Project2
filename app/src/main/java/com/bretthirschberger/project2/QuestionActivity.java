package com.bretthirschberger.project2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Toast;

public class QuestionActivity extends AppCompatActivity implements
        MultipleChoiceFragment.OnOptionSelectedListener, QuestionListFragment.OnQuestionListInteractionListener, MultipleAnswerFragment.OnOptionSelectedListener {

    private Question[] mQuestions;
    private QuestionListFragment mQuestionListFragment;
    private FragmentManager mFragmentManager;
    private int mQuestionNumber;
    private int mCorrectAnswers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        if(savedInstanceState!=null){
           mQuestionNumber=savedInstanceState.getInt("questionNumber");
           mCorrectAnswers=savedInstanceState.getInt("correctAnswers");
        }else{
            //creates dialogue window
            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.activity_rules);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
        }
        mFragmentManager=getSupportFragmentManager();
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
                        },4)
        };
        displayQuestion(mQuestions[mQuestionNumber]);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            displayQuestionList();
        }

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("questionNumber",mQuestionNumber);
        outState.getInt("correctAnswers",mCorrectAnswers);
    }

    private void displayQuestionList() {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        mQuestionListFragment = QuestionListFragment.newInstance();
        fragmentTransaction.add(R.id.questions_list, mQuestionListFragment);
        fragmentTransaction.commit();
    }

    private void displayQuestion(Question question) {
        MultipleAnswerFragment mMultipleAnswerFragment;
        MultipleChoiceFragment mMultipleChoiceFragment;
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        if (question instanceof MultipleChoiceQuestion) {
            mMultipleChoiceFragment=MultipleChoiceFragment.newInstance((MultipleChoiceQuestion) question);
            fragmentTransaction.replace(R.id.question_layout, mMultipleChoiceFragment);
        } else if (question instanceof MultipleAnswerQuestion) {
            mMultipleAnswerFragment=MultipleAnswerFragment.newInstance((MultipleAnswerQuestion) question);
            fragmentTransaction.replace(R.id.question_layout, mMultipleAnswerFragment);
        }
        fragmentTransaction.commit();
    }



    @Override
    public void onListItemChanged(int i) {
        displayQuestion(mQuestions[i]);
        mQuestionNumber=i;
    }

    @Override
    public void goToNext(boolean isCorrect) {
        if (isCorrect) {
            Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_SHORT).show();
            mCorrectAnswers++;
        }
        displayQuestion(mQuestions[++mQuestionNumber]);

    }

}
