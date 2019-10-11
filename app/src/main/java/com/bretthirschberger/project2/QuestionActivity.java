package com.bretthirschberger.project2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

public class QuestionActivity extends AppCompatActivity implements MultipleChoiceFragment.OnOptionSelectedListener {

    private MultipleChoiceQuestion[] mMultipleChoiceQuestions;
    private MultipleChoiceFragment mMultipleChoiceFragment;
//    private boolean isCorrect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        //creates dialogue window
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.activity_rules);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        mMultipleChoiceQuestions = new MultipleChoiceQuestion[]{
                new MultipleChoiceQuestion(getString(R.string.q1),
                        new String[]{
                                getString(R.string.q1a1),
                                getString(R.string.q1a2),
                                getString(R.string.q1a3),
                                getString(R.string.q1a4),
                        }, 1)
        };
        displayAnswers(0);

    }

    private void displayAnswers(int question) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        mMultipleChoiceFragment = MultipleChoiceFragment.newInstance(mMultipleChoiceQuestions[question]);
        fragmentTransaction.add(R.id.answers_layout, mMultipleChoiceFragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onOptionSelected(Boolean isCorrect) {
        if(isCorrect){
            Toast.makeText(getApplicationContext(),"Cortect",Toast.LENGTH_SHORT).show();
        }
        return isCorrect;
    }
}
