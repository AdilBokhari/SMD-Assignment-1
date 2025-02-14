package com.example.a1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Quiz extends AppCompatActivity {


    TextView tvQuesNo,tvQues;
    Button btnPrev,btnNext;
    RadioGroup rgAns;

    int totalQuestions=3;
    int currentQuestionIndex=0;

    public static String[] questions ={
            "Which planet is known as the Red Planet?",
            "What is the capital of Japan?",
            "Who painted the Mona Lisa?"
    };

    public static String[][] Choices ={
            {"Venus","Mars","Jupiter","Saturn"},
            {"Seoul","Beijing","Tokyo","Bangkok"},
            {"Vincent van Gogh","Pablo Picasso","Leonardo da Vinci","Michelangelo"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
        Intent i = getIntent();
        String name = i.getStringExtra("name");
        int quesNo=currentQuestionIndex+1;
        tvQuesNo.setText(quesNo+"/10");


    }

    private void init(){
        tvQuesNo=findViewById(R.id.tvQuesNo);
        tvQues=findViewById(R.id.tvQues);
        btnPrev=findViewById(R.id.btnPrev);
        btnNext=findViewById(R.id.btnNext);
        rgAns=findViewById(R.id.rgAns);
    }
}