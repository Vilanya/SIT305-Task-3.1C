package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //Activity 1 (First page)
    EditText nameEditText;
    Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEditText = (EditText) findViewById(R.id.editTextName);
        startButton = (Button) findViewById((R.id.start_button));
        startButton.setOnClickListener((View.OnClickListener) MainActivity.this);

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, QuizActivity.class);
        intent.putExtra("Name", nameEditText.getText().toString());
        startActivity(intent);
    }

}
