package com.example.quizapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ExitActivity extends AppCompatActivity {

    int score;
    int maxQuestions;
    String playerName;
    TextView congratsText;
    TextView scoreText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exit);

        congratsText = findViewById(R.id.congrats_text);
        scoreText = findViewById(R.id.score_text);

        Intent intent = getIntent();
        score = intent.getIntExtra("Score", 0);
        maxQuestions = intent.getIntExtra("MaxQuestions", 0);
        playerName = intent.getStringExtra("Name");

        congratsText.setText("Congratulations " + playerName + "!");
        scoreText.setText(score + "/" + maxQuestions);
    }

    public void OnClickRestart(View view) {
        finish();
    }

    public void OnClickFinish(View view) {
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }
}
