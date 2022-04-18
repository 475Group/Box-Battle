package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


public final class Players extends AppCompatActivity {
    private static String player1Name = "Player1", player2Name = "Player2";
    private static int p1Color = Color.parseColor("#B388FF"), p2Color = Color.parseColor("#FFD180");
    private static int p1Drawable = R.drawable.cookie_monster, p2Drawable =R.drawable.amethyst_universe;
    private static int setDrawable(int i){
        switch (i){
            case 0: return R.drawable.cookie_monster;
            case 1: return R.drawable.amethyst_universe;
            case 2: return R.drawable.homer_simpson;
            case 3: return R.drawable.jerry;
            case 4: return R.drawable.kermit_the_frog;
            default: return R.drawable.spongebob_squarepants;
        }
    }

    public static void setP1Drawable(int i) {
        p1Drawable = setDrawable(i);
    }

    public static void setP2Drawable(int i) {
        p2Drawable = setDrawable(i);
    }

    public static int getP1Drawable() {
        return p1Drawable;
    }

    public static int getP2Drawable() {
        return p2Drawable;
    }

    public static String getPlayer1Name() {
        return player1Name;
    }

    public static void setPlayer1Name(String name) {
        player1Name =name;
    }

    public static String getPlayer2Name() {
        return player2Name;
    }

    public static void setPlayer2Name(String name) {
        player2Name =name;
    }

    @SuppressLint("NewApi")
    public static int getPlayer1Color() {
        return p1Color;
    }

    @SuppressLint("NewApi")
    public static int getPlayer2Color() {
        return p2Color;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void setPlayer1Color(int color) {
        p1Color = color;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void setPlayer2Color(int color) {
        p2Color = color;
    }
}
