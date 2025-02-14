package com.example.a1;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Arrays;
import java.util.Objects;

public class Quiz extends AppCompatActivity {
    TextView tvQuesNo,tvQues;
    Button btnPrev,btnNext;
    RadioGroup rgAns;
    RadioButton rbOpt1,rbOpt2,rbOpt3,rbOpt4;
    ImageView ivArrowBack;
    int currentQuestionIndex=0;
    String[] questions ={
            "Which planet is known as the Red Planet?",
            "What is the capital of Japan?",
            "Who painted the Mona Lisa?",
            "What is the chemical symbol for gold?",
            "Which is the largest ocean on Earth?",
            "Which country won the FIFA World Cup in 2018?",
            "What is the square root of 144?",
            "Which famous scientist developed the theory of relativity?",
            "How many sides does a hexagon have?",
            "What is the national flower of Japan?"
    };
    int totalQuestions=questions.length;
    String[][] Choices ={
            {"Venus","Mars","Jupiter","Saturn"},
            {"Seoul","Beijing","Tokyo","Bangkok"},
            {"Vincent van Gogh","Pablo Picasso","Leonardo da Vinci","Michelangelo"},
            {"Ag","Au","Pb","Fe"},
            {"Atlantic Ocean","Indian Ocean","Arctic Ocean","Pacific Ocean"},
            {"Germany","Brazil","France","Argentina"},
            {"10","11","12","13"},
            {"Isaac Newton","Albert Einstein","Galileo Galilei","Stephen Hawking"},
            {"4","5","6","7"},
            {"Rose","Tulip","Cherry Blossom","Orchid"}
    };
    String[] UserAnswers=new String[totalQuestions];
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
        tvQuesNo.setText("Q. "+quesNo);
        loadQuestion();
        btnPrev.setVisibility(INVISIBLE);
        ivArrowBack.setVisibility(INVISIBLE);
        btnNext.setOnClickListener((v)->{
            if (btnNext.getText().toString().equals("End Quiz")) {
                int choice=rgAns.getCheckedRadioButtonId();
                    if (choice!=-1){
                        RadioButton rbOpt=findViewById(choice);
                        String answer=rbOpt.getText().toString();
                        UserAnswers[currentQuestionIndex]=answer;
                    }
                    else {
                        UserAnswers[currentQuestionIndex] = "";
                    }
                Intent in =new Intent(Quiz.this, Result.class);
                in.putExtra("name",name);
                in.putExtra("answers",UserAnswers);
                startActivity(in);
                finish();
                return;
            }
            int choice=rgAns.getCheckedRadioButtonId();
            if (choice!=-1){
                RadioButton rbOpt=findViewById(choice);
                String answer=rbOpt.getText().toString();
                UserAnswers[currentQuestionIndex]=answer;
                currentQuestionIndex++;
                loadQuestion();
                int ques=currentQuestionIndex+1;
                tvQuesNo.setText("Q. "+ques);
            }
            else{
                UserAnswers[currentQuestionIndex]="";
                currentQuestionIndex++;
                loadQuestion();
                int ques=currentQuestionIndex+1;
                tvQuesNo.setText("Q. "+ques);
            }
            btnPrev.setVisibility(VISIBLE);
            ivArrowBack.setVisibility(VISIBLE);
            if(currentQuestionIndex+1==totalQuestions){
                btnNext.setText("End Quiz");
            }
        });
        btnPrev.setOnClickListener((v)->{
            btnNext.setText("Next");
            currentQuestionIndex--;
            loadQuestion();
            if(currentQuestionIndex==0){
                btnPrev.setVisibility(INVISIBLE);
                ivArrowBack.setVisibility(INVISIBLE);
            }
            int ques=currentQuestionIndex+1;
            tvQuesNo.setText("Q. "+ques);
        });
    }

    private void loadQuestion(){
        tvQues.setText(questions[currentQuestionIndex]);
        rbOpt1.setText(Choices[currentQuestionIndex][0]);
        rbOpt2.setText(Choices[currentQuestionIndex][1]);
        rbOpt3.setText(Choices[currentQuestionIndex][2]);
        rbOpt4.setText(Choices[currentQuestionIndex][3]);
        rgAns.clearCheck();
        if(!Objects.equals(UserAnswers[currentQuestionIndex], "")){
            String selected=UserAnswers[currentQuestionIndex];
            for (int j = 0; j < 4; j++) {
                RadioButton value= (RadioButton) rgAns.getChildAt(j);
                if(value.getText().toString().equals(selected)){
                    rgAns.check(value.getId());
                }
            }
        }
    }

    private void init(){
        tvQuesNo=findViewById(R.id.tvQuesNo);
        tvQues=findViewById(R.id.tvQues);
        btnPrev=findViewById(R.id.btnPrev);
        btnNext=findViewById(R.id.btnNext);
        rgAns=findViewById(R.id.rgAns);
        rbOpt1=findViewById(R.id.rbOpt1);
        rbOpt2=findViewById(R.id.rbOpt2);
        rbOpt3=findViewById(R.id.rbOpt3);
        rbOpt4=findViewById(R.id.rbOpt4);
        ivArrowBack=findViewById(R.id.ivArrowBack);
    }
}