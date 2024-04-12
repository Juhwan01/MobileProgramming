package com.example.second;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    ImageButton imageButton;
    int currentImageIndex = 0;
    int[] imageArray = {R.drawable.btn_rating_star_off_disabled_focused_holo_light, R.drawable.btn_rating_star_off_disabled_holo_dark
            , R.drawable.btn_rating_star_off_disabled_holo_light, R.drawable.btn_rating_star_off_focused_holo_light, R.drawable.btn_rating_star_off_focused_holo_dark};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageButton = findViewById(R.id.imageButton);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentImageIndex = (currentImageIndex + 1) % imageArray.length;
                imageButton.setImageResource(imageArray[currentImageIndex]);
            }
        });
    }
}