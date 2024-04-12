package com.example.lab9;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private SingleTouchView drawView;
    private ImageButton currPaint;

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
        drawView = (SingleTouchView) findViewById(R.id.drawing);
        LinearLayout paintLayout = (LinearLayout) findViewById(R.id.paint_colors);
        currPaint = (ImageButton) paintLayout.getChildAt(0);
        drawView.setColor(currPaint.getTag().toString());
    }

    public void clicked(View v) {
        if (v != currPaint) {
            String color = v.getTag().toString();
            drawView.setColor(color);
            currPaint = (ImageButton) v;
        }
    }

    public void onClear(View v)
    {
        drawView.ClearCan();
    }
    public void OngetTri(View v)
    {
        drawView.type = 1;
        switch (drawView.type_shape)
        {
            case 0:
                drawView.type_shape = 1;
                break;
            case 1:
                drawView.type_shape = 2;
                break;
            case 2:
                drawView.type_shape = 3;
                break;
            case 3:
            drawView.type_shape = 0;
            break;
        }
    }
    public void Ondrawin(View v)
    {
        drawView.type = 0;
    }
    public void OnSetting(View v)
    {
        drawView.type = 2;
        drawView.tf = false;
    }

}