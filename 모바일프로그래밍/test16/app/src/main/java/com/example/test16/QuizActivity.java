package com.example.test16;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
public class QuizActivity extends AppCompatActivity{
    private RadioButton radioJava; // 정답: Java

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        radioJava = findViewById(R.id.radioJava);
        Button btnCheckResult = findViewById(R.id.btnCheckResult);

        btnCheckResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });
    }

    private void checkAnswer() {
        RadioGroup radioGroupOptions = findViewById(R.id.radioGroupOptions);
        int selectedId = radioGroupOptions.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(selectedId);

        if (radioButton == null) {
            Toast.makeText(this, "답을 선택하세요", Toast.LENGTH_SHORT).show();
            return;
        }

        if (radioButton.getId() == radioJava.getId()) {
            Toast.makeText(this, "정답입니다!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "틀렸습니다!", Toast.LENGTH_SHORT).show();
        }

        // 현재 QuizActivity를 종료하여 MainActivity로 복귀
        finish();
    }
}
