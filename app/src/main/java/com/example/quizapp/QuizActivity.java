package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {

    int current_question = 1;
    int max_questions = 5;
    String player_name;
    int score = 0;
    int completed = 0;
    TextView welcomeText;
    TextView quizCount;
    ProgressBar progressBar;
    TextView questionTitle;
    TextView questionText;
    Button answerOne;
    Button answerTwo;
    Button answerThree;
    int button_pressed;

    ArrayList<String> QuestionTitle = new ArrayList<String>();
    ArrayList<String> QuestionText = new ArrayList<String>();
    ArrayList<String[]> Answers = new ArrayList<String[]>();
    ArrayList<int[]> AnswerCheck = new ArrayList<int[]>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Intent intent = getIntent();
        player_name = intent.getStringExtra("Name");

        welcomeText = findViewById(R.id.welcome_text);
        quizCount = findViewById(R.id.quiz_count);
        progressBar = findViewById(R.id.progress_bar);
        questionTitle= findViewById(R.id.question_title);
        questionText = findViewById(R.id.question_text);
        answerOne = findViewById(R.id.answer1);
        answerTwo = findViewById(R.id.answer2);
        answerThree = findViewById(R.id.answer3);

        progressBar.setMax(max_questions);
        welcomeText.setText("Welcome " + player_name + "!");

        QuestionTitle.add("Question 1 - Activities");
        QuestionText.add("What is the first Activity callback and is called when an activity is first created?");
        Answers.add(new String[]{"onStart()","onResume()","onCreate()"});
        AnswerCheck.add(new int[]{0,0,1});

        QuestionTitle.add("Question 2 - OS");
        QuestionText.add("Which is not an operating system?");
        Answers.add(new String[]{"Android","iOS","Python"});
        AnswerCheck.add(new int[]{0,0,1});

        QuestionTitle.add("Question 3 - System Apps");
        QuestionText.add("Which is not a core system app in Android?");
        Answers.add(new String[]{"Instagram","Camera","Dialer"});
        AnswerCheck.add(new int[]{1,0,0});

        QuestionTitle.add("Question 4 - Open Source");
        QuestionText.add("Which of the following is an open source operating system?");
        Answers.add(new String[]{"MsWindows","Android","OS X"});
        AnswerCheck.add(new int[]{0,1,0});

        QuestionTitle.add("Question 5 - Controls");
        QuestionText.add("Which of the following is a user input control in Android Development?");
        Answers.add(new String[]{"TextView","LinearLayout","Constraints"});
        AnswerCheck.add(new int[]{1,0,0});

        progressBar.setProgress(current_question - 1);
        quizCount.setText(current_question + "/" + max_questions);

        Display();

    }

    public void OnClickSubmit(View view) {

        Button b = (Button)view;
        String buttonText = b.getText().toString();

        //Toast.makeText(this, "Submit pressed" + button_pressed, Toast.LENGTH_SHORT).show();

        if (buttonText.equals("Submit")){

            if (button_pressed == 0){
                CheckAnswers(0, answerOne);
            }
            else if (button_pressed == 1){
                CheckAnswers(1, answerTwo);
            }
            else if (button_pressed == 2){
                CheckAnswers(2, answerThree);
            }

            b.setText("Next");
        }
        else{
            if (current_question + 1 > max_questions) {
                //Toast.makeText(this, "Score " + score, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, ExitActivity.class);
                intent.putExtra("Score", score);
                intent.putExtra("MaxQuestions", max_questions);
                intent.putExtra("Name", player_name);
                startActivity(intent);
                finish();
            }
            else{
                current_question += 1;
                progressBar.setProgress(current_question - 1);
                quizCount.setText(current_question + "/" + max_questions);
                b.setText("Submit");

                Display();
            }
        }
    }

    public void OnClickAnsOne(View view){
        answerOne.setBackgroundColor(Color.parseColor("#FFFF99")); //yellow
        button_pressed = 0;
        //Toast.makeText(this, "Ans one pressed " + button_pressed, Toast.LENGTH_SHORT).show();

    }

    public void OnClickAnsTwo(View view){
        answerTwo.setBackgroundColor(Color.parseColor("#FFFF99")); //yellow
        button_pressed = 1;
        //Toast.makeText(this, "Ans 2 pressed " + button_pressed, Toast.LENGTH_SHORT).show();
    }
    public void OnClickAnsThree(View view){
        answerThree.setBackgroundColor(Color.parseColor("#FFFF99")); //yellow
        button_pressed = 2;
        //Toast.makeText(this, "Ans 2 pressed " + button_pressed, Toast.LENGTH_SHORT).show();
    }

    private void Display() {
        answerOne.setBackgroundColor(Color.parseColor("#D3D3D3")); //grey
        answerTwo.setBackgroundColor(Color.parseColor("#D3D3D3"));
        answerThree.setBackgroundColor(Color.parseColor("#D3D3D3"));

        int index = current_question - 1;

        questionTitle.setText(QuestionTitle.get(index));
        questionText.setText(QuestionText.get(index));
        answerOne.setText(Answers.get(index)[0]);
        answerTwo.setText(Answers.get(index)[1]);
        answerThree.setText(Answers.get(index)[2]);
    }

    private void CheckAnswers(int choice, Button b){

        //Toast.makeText(this, "Check answers entered " + choice + b.getText(), Toast.LENGTH_SHORT).show();
        answerOne.setBackgroundColor(Color.parseColor("#D3D3D3")); //grey
        answerTwo.setBackgroundColor(Color.parseColor("#D3D3D3"));
        answerThree.setBackgroundColor(Color.parseColor("#D3D3D3"));

        if (completed == current_question) return;

        completed += 1;

        int idx = current_question - 1;
        if (AnswerCheck.get(idx)[choice] == 1){

            score += 1;
            b.setBackgroundColor(Color.parseColor("#90ee90")); //green
        }
        else {
            //Toast.makeText(this, "Choice before green " + choice, Toast.LENGTH_SHORT).show();
            b.setBackgroundColor(Color.parseColor("#FF0000")); //red
            //Toast.makeText(this, "Choice before red " + choice, Toast.LENGTH_SHORT).show();
            if (AnswerCheck.get(idx)[0] == 1)
                answerOne.setBackgroundColor(Color.parseColor("#90ee90")); //green
            if (AnswerCheck.get(idx)[1] == 1)
                answerTwo.setBackgroundColor(Color.parseColor("#90ee90"));
            if (AnswerCheck.get(idx)[2] == 1)
                answerThree.setBackgroundColor(Color.parseColor("#90ee90"));
        }
    }
}
