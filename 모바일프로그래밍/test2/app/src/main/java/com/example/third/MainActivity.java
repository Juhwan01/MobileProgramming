package com.example.third;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ImageButton img1 = (ImageButton) findViewById(R.id.imageButton2);
        ImageButton img2 = (ImageButton) findViewById(R.id.imageButton3);
        ImageButton img3 = (ImageButton) findViewById(R.id.imageButton4);
        ImageButton img4 = (ImageButton) findViewById(R.id.imageButton5);
        ImageButton img5 = (ImageButton) findViewById(R.id.imageButton6);
        img1.setOnClickListener(e -> {
            img2.setVisibility(View.VISIBLE);
            img1.setVisibility(View.INVISIBLE);

        });
        img2.setOnClickListener(e -> {
            img3.setVisibility(View.VISIBLE);
            img2.setVisibility(View.INVISIBLE);

        });
        img3.setOnClickListener(e -> {
            img4.setVisibility(View.VISIBLE);
            img3.setVisibility(View.INVISIBLE);

        });
        img4.setOnClickListener(e -> {
            img5.setVisibility(View.VISIBLE);
            img4.setVisibility(View.INVISIBLE);

        });


    }
}