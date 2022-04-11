package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class GamePage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new CustomView(this));

    }
    public static class CustomView extends View {
        Paint paint;
        public CustomView(Context context) {
            super(context);
            paint = new Paint();
            paint.setColor(Color.BLACK);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawColor(Color.WHITE);
            float a = 2, b = 3*a;
            float mWidth = (float)this.getResources().getDisplayMetrics().widthPixels/ 10;
            float mHeight = (float)this.getResources().getDisplayMetrics().heightPixels/ 10;
            //Log.println(Log.ASSERT,mWidth+ "", mWidth +"");
            for (int i = 1; i < 4; i++) {
                for (int j = 1; j < 4; j++) {
                    canvas.drawCircle(mWidth, mHeight, 10, paint);
                }
            }
        }
    }
}