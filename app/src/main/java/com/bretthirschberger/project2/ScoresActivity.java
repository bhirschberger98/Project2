package com.bretthirschberger.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ScoresActivity extends AppCompatActivity {

    private TextView mScoreView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);
        mScoreView = findViewById(R.id.scores_view);
        mScoreView.setText(getScores());
    }

    private String getScores() {
        String scores = "";
        try (Scanner reader = new Scanner(new File(getFilesDir().getAbsolutePath() + "/scores.txt"))) {
            while (reader.hasNextLine()) {
                scores += reader.nextLine()+"\n";
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return scores;
    }

    public void playAgain(View view) {
        startActivity(new Intent(this, QuestionActivity.class).putExtra("email", getIntent().getStringExtra("email")));
    }

    public void logOut(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }
}
