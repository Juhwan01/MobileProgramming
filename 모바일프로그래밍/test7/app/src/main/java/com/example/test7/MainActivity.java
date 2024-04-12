package com.example.test7;

import android.os.Bundle;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    protected class MyView extends View{
        int x = 500, y = 500;
        String str;

        Paint circlePaint;
        Paint textPaint;
        int []colors = {Color.RED, Color.BLUE, Color.GREEN};
        int currentColorIndex = 0;

        public MyView(Context context)
        {
            super(context);
            setBackgroundColor(Color.YELLOW);
            circlePaint = new Paint();
            circlePaint.setTextSize(50);

            textPaint = new Paint();
            textPaint.setColor(Color.RED);
            textPaint.setTextSize(50);
        }

        protected void onDraw(Canvas canvas)
        {
            circlePaint.setColor(colors[currentColorIndex]);
            canvas.drawCircle(x,y,100,circlePaint);
            canvas.drawText("액션의종류: " + str, 0, 100, textPaint);
        }
        public boolean onTouchEvent(MotionEvent event)
        {
            x = (int) event.getX();
            y = (int) event.getY();
            switch(event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    str = "ACTION_DOWN";
                    break;
                case MotionEvent.ACTION_MOVE:
                    str = "ACTION_MOVE";
                    break;
                case MotionEvent.ACTION_UP:
                    changeColor();
                    str = "ACTION_UP";
                    break;
            }
            invalidate();
            return true;
        }
        private void changeColor()
        {
            currentColorIndex = (currentColorIndex + 1)% colors.length;
            circlePaint.setColor(colors[currentColorIndex]);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyView w = new MyView(this);
        setContentView(w);
    }

}