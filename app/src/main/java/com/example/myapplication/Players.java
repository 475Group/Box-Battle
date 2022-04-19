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

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public final class Players extends AppCompatActivity {
    private static String player1Name = "Player1", player2Name = "Player2";
    private static int p1Color = Color.parseColor("#B388FF"), p2Color = Color.parseColor("#FFD180");
    private static int p1Drawable = R.drawable.cookie_monster, p2Drawable =R.drawable.amethyst_universe;
    public static int avatar1 = 0, avatar2 = 1;
    public static int selectedColor1 = 5, selectedColor2 = 2;
    private static int player1Score = 0, player2Score = 0, turn;

    public static int getPlayer1Score() {
        return player1Score;
    }

    public static int getPlayer2Score() {
        return player2Score;
    }

    public static void setPlayer1Score(int player1Score) {
        Players.player1Score = player1Score;
    }

    public static void setPlayer2Score(int player2Score) {
        Players.player2Score = player2Score;
    }

    private static void selectTurn(){
        turn = (int) Math.round(Math.random());
    }

    public static int getTurn() {
        return turn;
    }

    public static void setTurn(){turn++;}

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
    public static void clear(){
        selectTurn();
        player1Name = "Player1";
        player2Name = "Player2";
        p1Drawable = R.drawable.cookie_monster;
        p2Drawable = R.drawable.amethyst_universe;
        player1Score = player2Score = 0;
        avatar1 = 0;
        avatar2 = 1;

    }
    public static void setP1Drawable(int i) {
        avatar1 = i;
        p1Drawable = setDrawable(i);
    }

    public static void setP2Drawable(int i) {
        avatar2 = i;
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
    public static void setPlayer1Color(int color, int i) {
        selectedColor1 = i;
        p1Color = color;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void setPlayer2Color(int color, int i) {
        selectedColor2 = i;
        p2Color = color;
    }
}
