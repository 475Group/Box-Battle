package com.example.myapplication;

import android.view.View;

import java.util.ArrayList;

public class Board {
    private int length, height;
    private String graphType;
    private int layout;

    public Board() {
        try {
            setSize(3,2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Board(int length, int height) {
        try {
            setSize(length,height);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getTotalWins(){
        return length*height;
    }

    public int getNumOflines(){
        return length * (height+1) + height * (length+1);
    }
    public int getHeight() {
        return height;
    }

    public int getLength() {
        return length;
    }

    public int getLayout() {
        return layout;
    }

    public String getGraphType() {
        return graphType;
    }

    public int getNumOfHorizontalLines(){
        return length * (height+1);
    }

    private void setSize(int l, int h) throws Exception {
        String size = l + "x" + h;
        graphType = "_"+size;
        switch(size){
            case "3x2":
                length = 3;
                height = 2;
                layout = R.layout.activity_game_page_3x2;
                break;
            case "5x4":
                length = 5;
                height = 4;
                layout = R.layout.activity_game_page_5x4;
                break;
            case "8x6":
                length = 8;
                height = 6;
                layout = R.layout.activity_game_page_8x6;
                break;
            case "11x9":
                length = 11;
                height = 9;
                layout = R.layout.activity_game_page_11x9;
                break;
            default:
                throw new Exception("Size Must Be: 3x2, 5x4, 8x6, or, 11x9");
        }
    }
}
