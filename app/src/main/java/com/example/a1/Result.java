package com.example.a1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;

public class Result extends AppCompatActivity {
    TextView tvName,tvScore;
    Button btnSubmit,btnHome;
    String[] Answers ={
            "Mars",
            "Tokyo",
            "Leonardo da Vinci",
            "Au",
            "Pacific Ocean",
            "France",
            "12",
            "Albert Einstein",
            "6",
            "Cherry Blossom"
    };
    int total=Answers.length;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();
        Intent i = getIntent();
        String name = i.getStringExtra("name");
        String[] UserAnswers = getIntent().getStringArrayExtra("answers");
        tvName.setText(name);
        int Score=0;
        for (int j = 0; j < Answers.length; j++) {
            if (Objects.equals(Answers[j], UserAnswers[j])){
                Score++;
            }
        }
        StringBuilder finalScore=new StringBuilder();
        finalScore.append(Score);
        finalScore.append("/");
        finalScore.append(total);
        tvScore.setText(finalScore);
        StringBuilder shareMsg=new StringBuilder();
        shareMsg.append(name);
        shareMsg.append(" scored ");
        shareMsg.append(finalScore);
        shareMsg.append(" in Quiz Khelo App.");
        btnSubmit.setOnClickListener((v)->{
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_TEXT,shareMsg.toString());
            intent.setType("text/*");
            startActivity(intent);
        });
        btnHome.setOnClickListener((v)->{
            Intent home=new Intent(Result.this, NamePage.class);
            startActivity(home);
            finish();
        });
    }
    private void init()
    {
        tvName=findViewById(R.id.tvName);
        tvScore=findViewById(R.id.tvScore);
        btnSubmit=findViewById(R.id.btnSubmit);
        btnHome=findViewById(R.id.btnHome);
    }
}