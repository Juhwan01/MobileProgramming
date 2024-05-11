package com.example.test15;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
public class QuizActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new QuizFragment()).commit();
    }

    public void changeButtonLabel(String label) {
        Button btnCheckResult = findViewById(R.id.btnCheckResult);
        btnCheckResult.setText(label);
    }
}
