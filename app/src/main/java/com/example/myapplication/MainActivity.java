package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;

import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView player1Score, player2Score, player1Text, player2Text, player1Turn, player2Turn;
    String player1Name, player2Name;
    int player1Color, player2Color;

    ImageView avatarP1, avatarP2;
    String player1Avatar, player2Avatar;
    int imageResource1, imageResource2;

    int player1ScoreValue = 0, player2ScoreValue = 0;
    View previousLine = null;
    int turn = 0;
    ArrayList<View> lines = new ArrayList<>();
    ArrayList<TextView> wins = new ArrayList<>();
    int numOfLines;

    public void setUpPlayerInfo(){
        if (!(player1Name.equals(""))) {
            player1Text.setText(player1Name);

        }
        player1Text.setTextColor(player1Color);
        player1Score.setTextColor(player1Color);

        if (!(player2Name.equals(""))) {
            player2Text.setText(player2Name);
        }
        player2Text.setTextColor(player2Color);
        player2Score.setTextColor(player2Color);

        System.out.println(player1Avatar);
             imageResource1 = getResources().getIdentifier(player1Avatar, null, getPackageName());
            avatarP1 = findViewById(R.id.avatarP1);
            Drawable res1 = getResources().getDrawable(imageResource1);
            avatarP1.setImageDrawable(res1);



             imageResource2 = getResources().getIdentifier(player2Avatar, null, getPackageName());
            avatarP2 = findViewById(R.id.avatarP2);
            Drawable res2 = getResources().getDrawable(imageResource2);
            avatarP2.setImageDrawable(res2);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Remove title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Hides action bar
        if(getSupportActionBar() != null){
            getSupportActionBar().hide();
        }


        setContentView(R.layout.activity_game_page);
        player1Text = findViewById(R.id.player1Text);
        player1Name = getIntent().getExtras().getString("P1");
        player1Color = getIntent().getIntExtra("colorP1", -16777216);
        player2Text = findViewById(R.id.player2Text);
        player2Name = getIntent().getExtras().getString("P2");
        player2Color = getIntent().getIntExtra("colorP2", -16777216);
        player1Avatar = "@drawable/" + getIntent().getExtras().getString("avatarP1");
        player2Avatar = "@drawable/" + getIntent().getExtras().getString("avatarP2");
        avatarP1 = findViewById(R.id.avatarP1);
        avatarP2 = findViewById(R.id.avatarP2);
        player1Score = findViewById(R.id.player1Score);
        player2Score = findViewById(R.id.player2Score);
        player1Turn = findViewById(R.id.Player1Turn);
        player2Turn = findViewById(R.id.Player2Turn);
        setUpPlayerInfo();
        ImageView settingsButton = findViewById(R.id.settingsIcon);
        ImageView infoButton = findViewById(R.id.infoIcon);


        settingsButton.setOnClickListener(view -> {
            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
            alertDialog.setTitle("Settings");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    (dialog, which) -> dialog.dismiss());

            alertDialog.show();
        });


        /*infoButton.setOnClickListener(view -> {
            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
            alertDialog.setTitle("Info");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    (dialog, which) -> dialog.dismiss());

            alertDialog.show();
        });*/

        //Open info page
        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InfoActivity.class);
                startActivity(intent);
                //finish();
                /*openInfoActivity();
                finish();*/
            }
        });

        clearBoard();
        Button confirmButton = findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(view -> {
            if(previousLine != null) {
                previousLine.setClickable(false);
                boolean stillLastPlayerTurn = checkWin(previousLine);
                int boardSize = 6;
                if (player2ScoreValue + player1ScoreValue == boardSize)
                {
                    AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                    if (player1ScoreValue > player2ScoreValue) {
                        alertDialog.setTitle(player1Text.getText() + " Wins!");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "CANCEL",
                                (dialog, which) -> dialog.dismiss());
                        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "QUIT",
                                (dialog, which) -> openStartScreen());
                    }
                    if (player1ScoreValue < player2ScoreValue) {
                        alertDialog.setTitle(player2Text.getText() + " Wins!");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "CANCEL",
                                (dialog, which) -> dialog.dismiss());
                        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "QUIT",
                                (dialog, which) -> openStartScreen());
                    }
                    alertDialog.show();
                    previousLine = null;
                }
                else {
                    if(!stillLastPlayerTurn){
                        if (turn %2 == 0 ) {
                            player1Turn.setVisibility(View.INVISIBLE);
                            player2Turn.setVisibility(View.VISIBLE);
                        }
                        else {
                            player2Turn.setVisibility(View.INVISIBLE);
                            player1Turn.setVisibility(View.VISIBLE);
                        }
                        turn++;
                    }
                    previousLine = null;
                }
            }
        });
        Button quitButton = findViewById(R.id.quitButton);
        quitButton.setOnClickListener(view -> {
            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
            alertDialog.setTitle("Are you sure you want to quit?");
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "YES",
                    (dialog, which) -> openStartScreen());
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "CANCEL",
                    (dialog, which) -> dialog.dismiss());

            alertDialog.show();
        });
        Button restartButton = findViewById(R.id.restartButton);
        restartButton.setOnClickListener(view -> {
            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
            alertDialog.setTitle("Are you sure you want to restart?");
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "YES",
                    (dialog, which) -> clearBoard());
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "CANCEL",
                    (dialog, which) -> dialog.dismiss());

            alertDialog.show();
        });
    }

    @Override
    public void onClick(View view) {
        if (previousLine == null) {
            ViewGroup.LayoutParams params = view.getLayoutParams();
            if (params.width == 130)
                params.width = 20;
            else
                params.height = 20;
            view.setLayoutParams(params);

            if (turn %2 == 0) {
                view.setBackgroundColor(Color.BLACK);

            }
            if (turn %2 != 0) {
                view.setBackgroundColor(Color.BLACK);
            }
        }
        if (previousLine != null){
            previousLine.setBackgroundColor(findViewById(R.id.square).getSolidColor());
            ViewGroup.LayoutParams params = previousLine.getLayoutParams();
            if (params.width == 20)
                params.width = 130;
            else
                params.height = 130;
            previousLine.setLayoutParams(params);

            ViewGroup.LayoutParams params0 = view.getLayoutParams();
            if (params0.width == 130)
                params0.width = 20;
            else
                params0.height = 20;
            view.setLayoutParams(params0);
            if (turn %2 == 0) {
                view.setBackgroundColor(Color.BLACK);
            }
            if (turn %2 != 0) {
                view.setBackgroundColor(Color.BLACK);
            }
        }

        previousLine = view;
    }

    @SuppressLint("SetTextI18n")
    public boolean checkWin(View line){
        lines.add(line);
        Resources r = getResources();
        String name = getPackageName();
        boolean thereWasAWinner = false;
        int firstCondition;
        int secondCondition;
        int thirdCondition;
        int fourthCondition;
        int k = 0;
        int lastLineInRowsNMin1 = 6;
        int numOfRows = 3;
        int numOfRowLines = 9;
        String graphType = "_2x3";
        for (int i = 1; i <= lastLineInRowsNMin1; i++){
            firstCondition = i;
            secondCondition = i+numOfRows;
            if (i-1 != 0 && (i-1)%numOfRows == 0) {
                thirdCondition = i + numOfRowLines + 1 + k;
                fourthCondition = i + numOfRowLines + 2 + k;
                k++;
            }
            else {
                thirdCondition = i+numOfRowLines + k;
                fourthCondition = i + numOfRowLines+1 + k;
            }
            if (lines.contains(findViewById(r.getIdentifier("line" + firstCondition + graphType, "id", name))) &&
                    lines.contains(findViewById(r.getIdentifier("line" + secondCondition + graphType, "id", name)))&&
                    lines.contains(findViewById(r.getIdentifier("line" + thirdCondition + graphType, "id", name)))&&
                    lines.contains(findViewById(r.getIdentifier("line" + fourthCondition + graphType, "id", name)))){
                if(line.equals(findViewById(r.getIdentifier("line" + firstCondition + graphType, "id", name))) ||
                    line.equals(findViewById(r.getIdentifier("line" + secondCondition + graphType, "id", name))) ||
                        line.equals(findViewById(r.getIdentifier("line" + thirdCondition + graphType, "id", name)))||
                            line.equals(findViewById(r.getIdentifier("line" + fourthCondition + graphType, "id", name)))){

                    if (turn % 2 == 0) {
                        player1ScoreValue++;
                        player1Score.setText("Score: " + player1ScoreValue);
                        wins.get(i-1).setTextColor(player1Text.getCurrentTextColor());
                    } else {
                        player2ScoreValue++;
                        player2Score.setText("Score: " + player2ScoreValue);
                        wins.get(i-1).setTextColor(player2Text.getCurrentTextColor());
                    }

                    thereWasAWinner = true;

                }
            }


        }
        return thereWasAWinner;
    }
    @SuppressLint("SetTextI18n")
    public void clearBoard(){
        numOfLines = 0;
        player1Turn.setVisibility(View.VISIBLE);
        player2Turn.setVisibility(View.INVISIBLE);
        turn = 0;
        previousLine = null;
        lines.clear();
        wins.clear();
        player1ScoreValue = 0;
        player2ScoreValue = 0;
        player1Score.setText("Score: " + player1ScoreValue);
        player2Score.setText("Score: " + player2ScoreValue);
        Resources r = getResources();
        String name = getPackageName();
        int l = 17;
        int numOfWins = 6;
        for (int i = 1; i <= l; i++) {
            View line = findViewById(r.getIdentifier("line" + i + "_2x3", "id", name));
            ViewGroup.LayoutParams params = line.getLayoutParams();
            if (params.height < params.width)
                params.height = 130;
            else
                params.width = 130;
            line.setBackgroundColor(findViewById(R.id.square).getSolidColor());
            line.setLayoutParams(params);
            line.setOnClickListener(this);
            numOfLines++;
        }
        for (int i = 1; i <= numOfWins; i++)
        {
            TextView x = findViewById(r.getIdentifier("win"+i+"_2x3", "id", name));
            x.setTextColor(findViewById(R.id.square).getSolidColor());
            wins.add(x);
        }
    }
    public void openStartScreen(){
        Intent intent = new Intent(this, StartScreenActivity.class);
        startActivity(intent);
    }

    //Open info page
    public void openInfoActivity() {
        Intent intent = new Intent(this, InfoActivity.class);
        startActivity(intent);
    }
}