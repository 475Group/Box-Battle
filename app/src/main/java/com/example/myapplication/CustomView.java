package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class CustomView extends View {
    Paint paint;
    public CustomView(Context context) {
        super(context);
        paint = new Paint();
        paint.setColor(Color.BLACK);
    }

    @Override
    protected void onDraw(Canvas canvas) {
       canvas.drawColor(Color.BLACK);
       canvas.drawCircle(200,200,100, paint);
    }
}
