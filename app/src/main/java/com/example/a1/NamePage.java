package com.example.a1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class NamePage extends AppCompatActivity {
    Button btnNamePage;
    EditText etName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_name_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
        btnNamePage.setOnClickListener((v)->{
            String name=etName.getText().toString().trim();
            if(name.isEmpty()){
                etName.setError("Enter Name");
                return;
            }
            Intent i =new Intent(NamePage.this, Quiz.class);
            i.putExtra("name",name);
            startActivity(i);
            finish();
        });
    }
    private void init(){
        btnNamePage=findViewById(R.id.btnNamePage);
        etName=findViewById(R.id.etName);
    }
}