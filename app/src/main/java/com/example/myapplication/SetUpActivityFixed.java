package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Debug;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class SetUpActivityFixed extends AppCompatActivity {
    ArrayList<RadioButton> p1Avatars, p2Avatars, p1Colors, p2Colors;
    EditText p1Name, p2Name;
    Button _3x2,_5x4,_8x6,_11x9, startButton;
    ArrayList<Button> buttons;
    View prevButton = null;
    View prevAvatarp1 = null, prevAvatarp2 = null;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_screen);
        int[] colors = {ContextCompat.getColor(this,R.color.red),
                ContextCompat.getColor(this,R.color.orange),
                ContextCompat.getColor(this,R.color.yellow),
                ContextCompat.getColor(this,R.color.green),
                ContextCompat.getColor(this,R.color.blue),
                ContextCompat.getColor(this,R.color.purple)};
       p1Name = findViewById(R.id.inputTextP1);
       p2Name = findViewById(R.id.inputTextP2);
       p1Name.setHint(Players.getPlayer1Name());
       p2Name.setHint(Players.getPlayer2Name());

       p1Avatars = new ArrayList<>();
       p2Avatars = new ArrayList<>();
       p1Colors = new ArrayList<>();
       p2Colors = new ArrayList<>();
       buttons = new ArrayList<>();

       Resources r = getResources();
       String name = getPackageName();
       for (int i = 1; i <=6 ; i++) {
            String p1Avatar = "RB"+i+".1";
            String p2Avatar = "RB"+i+".2";
            String p1Color = "c"+i+".1";
            String p2Color = "c"+i+".2";
            p1Avatars.add(findViewById(r.getIdentifier(p1Avatar, "id",name)));
            p2Avatars.add(findViewById(r.getIdentifier(p2Avatar, "id",name)));
            p1Colors.add(findViewById(r.getIdentifier(p1Color, "id",name)));
            p2Colors.add(findViewById(r.getIdentifier(p2Color, "id",name)));
        }
        int originalColorForAvatar = p1Avatars.get(0).getSolidColor();
       prevAvatarp1 = p1Avatars.get(Players.avatar1);
       p1Avatars.get(Players.avatar1).setBackgroundColor(Color.BLUE);
        for (int i = 0; i < p1Avatars.size(); i++) {
            int finalI = i;
            p1Avatars.get(i).setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    if (prevAvatarp1 != null)
                        prevAvatarp1.setBackgroundColor(originalColorForAvatar);
                    view.setBackgroundColor(Color.BLUE);
                    prevAvatarp1 = view;
                    Players.setP1Drawable(finalI);
                }
            });
        }
        prevAvatarp2 = p2Avatars.get(Players.avatar2);
        p2Avatars.get(Players.avatar2).setBackgroundColor(Color.BLUE);
        for (int i = 0; i < p2Avatars.size(); i++) {
            int finalI = i;
            p2Avatars.get(i).setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    if (prevAvatarp2 != null)
                        prevAvatarp2.setBackgroundColor(originalColorForAvatar);
                    view.setBackgroundColor(Color.BLUE);
                    prevAvatarp2 = view;
                    Players.setP2Drawable(finalI);
                }
            });
        }
        p1Colors.get(Players.selectedColor1).setChecked(true);
        for (int i = 0; i < p1Colors.size(); i++) {
            int finalI = i;
            p1Colors.get(i).setOnClickListener(new View.OnClickListener(){

                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onClick(View view) {
                    Players.setPlayer1Color(colors[finalI], finalI);
                }
            });
        }
        p2Colors.get(Players.selectedColor2).setChecked(true);
        for (int i = 0; i < p2Colors.size(); i++) {
            int finalI = i;
            p2Colors.get(i).setOnClickListener(new View.OnClickListener(){

                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onClick(View view) {
                    Players.setPlayer2Color(colors[finalI], finalI);
                }
            });
        }
        _3x2 = findViewById(R.id._3x2);
        _5x4 = findViewById(R.id._5x4);
        _8x6 = findViewById(R.id._8x6);
        _11x9 = findViewById(R.id._11x9);
        buttons.add(_3x2);
        buttons.add(_5x4);
        buttons.add(_8x6);
        buttons.add(_11x9);

        prevButton = buttons.get(Board.layoutNumber);
        prevButton.setBackgroundColor(Color.BLUE);
        int originalColor = _3x2.getSolidColor();
        _3x2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (prevButton != null)
                    prevButton.setBackgroundColor(originalColor);
                view.setBackgroundColor(Color.BLUE);
                prevButton = view;
                Board.setSize(3,2);

            }
        });
        _5x4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (prevButton != null)
                    prevButton.setBackgroundColor(originalColor);
                view.setBackgroundColor(Color.BLUE);
                prevButton = view;
                Board.setSize(5,4);
            }
        });
        _8x6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (prevButton != null)
                    prevButton.setBackgroundColor(originalColor);
                view.setBackgroundColor(Color.BLUE);
                prevButton = view;
                Board.setSize(8,6);
            }
        });
        _11x9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (prevButton != null)
                    prevButton.setBackgroundColor(originalColor);
                view.setBackgroundColor(Color.BLUE);
                prevButton = view;
                Board.setSize(11,9);
            }
        });
        startButton = findViewById(R.id.confirmButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                if (!p1Name.getText().toString().equals(""))
                    Players.setPlayer1Name(p1Name.getText().toString());
                if (!p2Name.getText().toString().equals(""))
                    Players.setPlayer2Name(p2Name.getText().toString());
                if (Board.init == 0) {
                    Board.start();
                    Board.init = 1;
                }
                Intent intent = new Intent(SetUpActivityFixed.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onBackPressed() {
        finish();
    }
}
