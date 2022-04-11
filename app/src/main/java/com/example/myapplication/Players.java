package com.example.myapplication;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.widget.TextView;


public class Players {
    private TextView player1Name, player2Name;

    public Players(){
        setPlayer1Name("Player1");
        setPlayer1Color(Color.parseColor("#FF6D00"));
        setPlayer2Name("Player2");
        setPlayer2Color(Color.parseColor("#AA00FF"));

    }

    public Players(String p1, int p1Color, String p2, int p2Color){
        setPlayer1Name(p1);
        setPlayer1Color(p1Color);
        setPlayer2Name(p2);
        setPlayer2Color(p2Color);
    }

    public TextView getPlayer1Name() {
        return player1Name;
    }

    public void setPlayer1Name(String player1Name) {
        this.player1Name.setText(player1Name);
    }

    public TextView getPlayer2Name() {
        return player2Name;
    }

    public void setPlayer2Name(String player2Name) {
        this.player2Name.setText(player2Name);
    }

    @SuppressLint("NewApi")
    public Color getPlayer1Color() {
        return Color.valueOf(player1Name.getCurrentTextColor());
    }

    @SuppressLint("NewApi")
    public Color getPlayer2Color() {
        return Color.valueOf(player2Name.getCurrentTextColor());
    }

    public void setPlayer1Color(int player1Color) {
        this.player1Name.setTextColor(player1Color);
    }

    public void setPlayer2Color(int player2Color) {
        this.player2Name.setTextColor(player2Color);
    }
}
