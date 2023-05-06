package com.example.appquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.AlertDialog;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView TqTxt;
    TextView qTxtView;
    Button optA, optB, optC, optD;
    Button subBtn;

    int score=0;
    int totalQuest = QandA.question.length;
    int currentQuest = 0;
    String selectedOpt = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TqTxt = findViewById(R.id.total_quest);
        qTxtView = findViewById(R.id.question);
        optA = findViewById(R.id.opt_A);
        optB = findViewById(R.id.opt_B);
        optC = findViewById(R.id.opt_C);
        optD = findViewById(R.id.opt_D);
        subBtn = findViewById(R.id.submit_btn);

        optA.setOnClickListener(this);
        optB.setOnClickListener(this);
        optC.setOnClickListener(this);
        optD.setOnClickListener(this);
        subBtn.setOnClickListener(this);

        TqTxt.setText("Total questions : "+totalQuest);

        NextQuestion();

    }

    @Override
    public void onClick(View view) {

        optA.setBackgroundColor(Color.WHITE);
        optB.setBackgroundColor(Color.WHITE);
        optC.setBackgroundColor(Color.WHITE);
        optD.setBackgroundColor(Color.WHITE);

        Button clickedButton = (Button) view;
        if(clickedButton.getId()==R.id.submit_btn){
            if(selectedOpt.equals(QandA.correctAnswers[currentQuest])){
                score++;
            }
            currentQuest++;
            NextQuestion();

        }
        else{
            selectedOpt  = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.MAGENTA);
        }
    }

    void NextQuestion(){
        if(currentQuest == totalQuest ){
            EndQuiz();
            return;
        }

        qTxtView.setText(QandA.question[currentQuest]);
        optA.setText(QandA.choices[currentQuest][0]);
        optB.setText(QandA.choices[currentQuest][1]);
        optC.setText(QandA.choices[currentQuest][2]);
        optD.setText(QandA.choices[currentQuest][3]);
    }

    void EndQuiz(){
        String passStatus = "";
        if(score > totalQuest*0.60){
            passStatus = "Passed";
        }else{
            passStatus = "Failed";
        }

        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Score is "+ score+" out of "+ totalQuest)
                .setPositiveButton("Restart",(dialogInterface, i) -> RestartQuiz() )
                .setCancelable(false)
                .show();
    }

    void RestartQuiz(){
        score = 0;
        currentQuest = 0;
        NextQuestion();
    }
}