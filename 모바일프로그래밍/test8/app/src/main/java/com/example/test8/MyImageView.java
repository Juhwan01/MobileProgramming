package com.example.test8;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
public class MyImageView extends View{
    private Drawable image;
    private ScaleGestureDetector gestureDetector;
    private float scale = 1.0f;

    public MyImageView(Context context)
    {
        super(context);
        image = context.getResources().getDrawable(R.drawable.wa);
        setFocusable(true);
        image.setBounds(0,0, image.getIntrinsicWidth(),image.getIntrinsicHeight());
        gestureDetector = new ScaleGestureDetector(context, new ScaleListener());
    }
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        canvas.save();
        canvas.scale(scale,scale);
        image.draw(canvas);
        canvas.restore();
    }
    public boolean onTouchEvent(MotionEvent event)
    {
        gestureDetector.onTouchEvent(event);
        invalidate();
        return true;
    }
    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener
    {
        @Override
        public boolean onScale(ScaleGestureDetector detector)
        {
            scale *= detector.getScaleFactor();
            if (scale < 0.1f)
            {
                scale = 0.1f;
            }
            if (scale > 10.0f)
            {
                scale = 10.0f;
            }
            invalidate();
            return true;
        }
    }
}
