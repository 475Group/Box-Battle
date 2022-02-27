package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView player1Score, player2Score, player1Text, player2Text, player1Turn, player2Turn;
    int player1ScoreValue = 0, player2ScoreValue = 0;
    View previousLine = null;
    int turn = 0;
    ArrayList<View> lines = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        player1Text = (TextView) findViewById(R.id.player1Text);
        player2Text = (TextView) findViewById(R.id.player2Text);
        player1Score = (TextView) findViewById(R.id.player1Score);
        player2Score = (TextView) findViewById(R.id.player2Score);
        player1Turn = (TextView) findViewById(R.id.Player1Turn);
        player2Turn = (TextView) findViewById(R.id.Player2Turn);
        ImageView settingsButton = (ImageView) findViewById(R.id.settingsIcon);
        ImageView infoButton = (ImageView) findViewById(R.id.infoIcon);

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle("Settings");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                alertDialog.show();
            }
        });

        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle("Info");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                alertDialog.show();
            }
        });
        clearBoard();
        Button confirmButton = (Button) findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if(previousLine != null) {
                    if (turn %2 == 0) {
                        player1Turn.setVisibility(View.INVISIBLE);
                        player2Turn.setVisibility(View.VISIBLE);
                    }
                    if (turn %2 != 0) {
                        player2Turn.setVisibility(View.INVISIBLE);
                        player1Turn.setVisibility(View.VISIBLE);
                    }
                    previousLine.setClickable(false);
                    checkWin(previousLine, turn);
                    previousLine = null;
                    turn++;
                }
            }
        });
        Button quitButton = (Button) findViewById(R.id.quitButton);
        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle("Are you sure you want to quit?");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                clearBoard();
                            }
                        });
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "CANCEL",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();


            }
        });
        Button restartButton = (Button) findViewById(R.id.restartButton);
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle("Are you sure you want to restart?");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                clearBoard();
                            }
                        });
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "CANCEL",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();

            }
        });
    }

    @Override
    public void onClick(View view) {
        if (previousLine == null) {
            if (turn %2 == 0) {
                view.setVisibility(View.VISIBLE);
                view.setBackgroundColor(player1Text.getCurrentTextColor());
            }
            if (turn %2 != 0) {
                view.setVisibility(View.VISIBLE);
                view.setBackgroundColor(player2Text.getCurrentTextColor());
            }
        }
        if (previousLine != null){
            previousLine.setBackgroundColor(findViewById(R.id.GamePage4x4).getSolidColor());
            if (turn %2 == 0) {
                view.setVisibility(View.VISIBLE);
                view.setBackgroundColor(player1Text.getCurrentTextColor());
            }
            if (turn %2 != 0) {
                view.setVisibility(View.VISIBLE);
                view.setBackgroundColor(player2Text.getCurrentTextColor());
            }
        }

        previousLine = view;
    }

    @SuppressLint("SetTextI18n")
    public void checkWin(View line, int turn){
        lines.add(line);
        Resources r = getResources();
        String name = getPackageName();
        int firstCondition;
        int secondCondition;
        int thirdCondition;
        int fourthCondition;
        int k = 0;
        for (int i = 1; i <= 9; i++){
            firstCondition = i;
            secondCondition = i+3;
            if (i-1 != 0 && (i-1)%3 == 0) {
                thirdCondition = i + 12 + 1 + k;
                fourthCondition = i + 12 + 2 + k;
                k++;
            }
            else {
                thirdCondition = i+12 + k;
                fourthCondition = i + 12+1 + k;
            }
            System.out.println(firstCondition + " " + secondCondition + " " + thirdCondition + " " + fourthCondition);
            if (lines.contains(findViewById(r.getIdentifier("line" + firstCondition, "id", name))) &&
                    lines.contains(findViewById(r.getIdentifier("line" + secondCondition, "id", name)))&&
                    lines.contains(findViewById(r.getIdentifier("line" + thirdCondition, "id", name)))&&
                    lines.contains(findViewById(r.getIdentifier("line" + fourthCondition, "id", name)))){
                if(line.equals(findViewById(r.getIdentifier("line" + firstCondition, "id", name))) ||
                    line.equals(findViewById(r.getIdentifier("line" + secondCondition, "id", name))) ||
                        line.equals(findViewById(r.getIdentifier("line" + thirdCondition, "id", name)))||
                            line.equals(findViewById(r.getIdentifier("line" + fourthCondition, "id", name)))){

                    if (turn % 2 == 0) {
                        player1ScoreValue++;
                        player1Score.setText("Score: " + player1ScoreValue);
                    } else {
                        player2ScoreValue++;
                        player2Score.setText("Score: " + player2ScoreValue);
                    }
                }
            }


        }
    }
    @SuppressLint("SetTextI18n")
    public void clearBoard(){
        player1Turn.setVisibility(View.VISIBLE);
        player2Turn.setVisibility(View.INVISIBLE);
        turn = 0;
        previousLine = null;
        lines.clear();
        player1ScoreValue = 0;
        player2ScoreValue = 0;
        player1Score.setText("Score: " + player1ScoreValue);
        player2Score.setText("Score: " + player2ScoreValue);
        Resources r = getResources();
        String name = getPackageName();
        for (int i = 1; i < 25; i++) {
            View line = (View) findViewById(r.getIdentifier("line" + i, "id", name));
            line.setBackgroundColor(findViewById(R.id.GamePage4x4).getSolidColor());
            line.setOnClickListener(this);
        }
    }
}