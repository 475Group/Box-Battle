package com.example.myapplication;

import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public final class Board {
    private static int length = 3, height = 2;
    private static String graphType = "_3x2";
    private static int layout = R.layout.activity_game_page_3x2;
    public static ArrayList<View> lines;
    public static int init = 0, layoutNumber = 0;
    public static ArrayList<TextView> wins;
    public static View currLine = null;
    public static void start(){
        lines = new ArrayList<>();
        wins = new ArrayList<>();
    }
    public static void clear(){
        lines.clear();
        wins.clear();
    }
    public static int getTotalWins(){
        return length*height;
    }
    public static int getNumOflines(){
        return length * (height+1) + height * (length+1);
    }
    public static int getHeight() {
        return height;
    }

    public static int getLength() {
        return length;
    }

    public static int getLayout() {
        return layout;
    }

    public static String getGraphType() {
        return graphType;
    }

    public static int getNumOfHorizontalLines(){
        return length * (height+1);
    }

    public static void setSize(int l, int h){
        String size = l + "x" + h;
        graphType = "_"+size;
        switch(size){
            case "3x2":
                length = 3;
                height = 2;
                layout = R.layout.activity_game_page_3x2;
                layoutNumber = 0;
                break;
            case "5x4":
                length = 5;
                height = 4;
                layout = R.layout.activity_game_page_5x4;
                layoutNumber = 2;
                break;
            case "8x6":
                length = 8;
                height = 6;
                layout = R.layout.activity_game_page_5x5;
                layoutNumber = 3;
                break;
            case "3x3":
                length = 3;
                height = 3;
                layout = R.layout.activity_game_page_3x3;
                layoutNumber = 1;
                break;
            }
    }
}
