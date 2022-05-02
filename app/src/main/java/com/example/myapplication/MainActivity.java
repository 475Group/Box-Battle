package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;

import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final int PADDING = 130;
    //text palyer name and score shown on page
    TextView player1Score, player2Score, player1Text, player2Text, player1Turn, player2Turn;
    ImageView avatarP1, avatarP2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*//Remove title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Hides action bar
        if(getSupportActionBar() != null){
            getSupportActionBar().hide();
        }*/


        setContentView(Board.getLayout());
        //get views(these should be in their own class)
        avatarP1 = findViewById(R.id.avatarP1);
        avatarP1.setImageDrawable(getDrawable(Players.getP1Drawable()));

        avatarP2 = findViewById(R.id.avatarP2);
        avatarP2.setImageDrawable(getDrawable(Players.getP2Drawable()));

        player1Text = findViewById(R.id.player1Text);
        player1Text.setText(Players.getPlayer1Name());
        player1Text.setTextColor(Players.getPlayer1Color());

        player2Text = findViewById(R.id.player2Text);
        player2Text.setText(Players.getPlayer2Name());
        player2Text.setTextColor(Players.getPlayer2Color());

        player1Score = findViewById(R.id.player1Score);
        player1Score.setTextColor(Players.getPlayer1Color());

        player2Score = findViewById(R.id.player2Score);
        player2Score.setTextColor(Players.getPlayer2Color());

        player1Turn = findViewById(R.id.Player1Turn);
        player2Turn = findViewById(R.id.Player2Turn);

        ImageView settingsButton = findViewById(R.id.settingsIcon);
        ImageView infoButton = findViewById(R.id.infoIcon);


        //adding on click listener for settings button
        settingsButton.setOnClickListener(view -> {
            //opens a new intent to open the settings activity.
            Intent i = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(i);
        });

        //Open info page
        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InfoActivity.class);
                startActivity(intent);
            }
        });
            clearBoard();
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

        //alert for restart
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
    public void onBackPressed() {
        finish();
    }

    @Override
    public void onClick(View view) {
        if (!Board.lines.contains(view)) {

            ViewGroup.LayoutParams params = view.getLayoutParams();
            int size = 20;
            if (params.width == PADDING)
                params.width = size;
            else
                params.height = size;
            view.setLayoutParams(params);
            view.setBackgroundColor(Color.BLACK);
            Board.currLine = view;
            play();
        }

    }
    public void play(){
        //there must be a line selected
            //checks if current player got a win
            boolean stillLastPlayerTurn = checkWin();
            //checks if there was a win
            if (Players.getPlayer1Score() + Players.getPlayer2Score() == Board.getTotalWins())
            {
                //alerts players of winner
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                if (Players.getPlayer1Score() > Players.getPlayer2Score()) {
                    alertDialog.setTitle(player1Text.getText() + " Wins!");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "CANCEL",
                            (dialog, which) -> dialog.dismiss());
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "QUIT",
                            (dialog, which) -> openStartScreen());
                }
                else if (Players.getPlayer1Score() < Players.getPlayer2Score()) {
                    alertDialog.setTitle(player2Text.getText() + " Wins!");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "CANCEL",
                            (dialog, which) -> dialog.dismiss());
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "QUIT",
                            (dialog, which) -> openStartScreen());
                }
                else{
                    alertDialog.setTitle("TIE!");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "CANCEL",
                            (dialog, which) -> dialog.dismiss());
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "QUIT",
                            (dialog, which) -> openStartScreen());
                }
                alertDialog.show();
            }
            //otherwise switch players
            else {
                if(!stillLastPlayerTurn){
                    if (Players.getTurn() %2 == 0 ) {
                        player1Turn.setVisibility(View.INVISIBLE);
                        player2Turn.setVisibility(View.VISIBLE);
                    }
                    else {
                        player2Turn.setVisibility(View.INVISIBLE);
                        player1Turn.setVisibility(View.VISIBLE);
                    }
                    Players.setTurn();
                }

            }
        }
    //}
    @SuppressLint("SetTextI18n")
    public boolean checkWin(){
        Board.lines.add(Board.currLine);
        Resources r = getResources();
        String name = getPackageName();
        boolean thereWasAWinner = false;
        int firstCondition;
        int secondCondition;
        int thirdCondition;
        int fourthCondition;
        int k = 0;
        int lastLineInRowsNMin1 = Board.getNumOfHorizontalLines()-Board.getLength();
        int numOfRows = Board.getLength();
        int numOfRowLines = Board.getNumOfHorizontalLines();
        String graphType = Board.getGraphType();
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
            if (Board.lines.contains(findViewById(r.getIdentifier("line" + firstCondition + graphType, "id", name))) &&
                    Board.lines.contains(findViewById(r.getIdentifier("line" + secondCondition + graphType, "id", name)))&&
                    Board.lines.contains(findViewById(r.getIdentifier("line" + thirdCondition + graphType, "id", name)))&&
                    Board.lines.contains(findViewById(r.getIdentifier("line" + fourthCondition + graphType, "id", name)))){
                if(Board.currLine.equals(findViewById(r.getIdentifier("line" + firstCondition + graphType, "id", name))) ||
                    Board.currLine.equals(findViewById(r.getIdentifier("line" + secondCondition + graphType, "id", name))) ||
                        Board.currLine.equals(findViewById(r.getIdentifier("line" + thirdCondition + graphType, "id", name)))||
                            Board.currLine.equals(findViewById(r.getIdentifier("line" + fourthCondition + graphType, "id", name)))){

                    if (Players.getTurn() % 2 == 0) {
                        Players.setPlayer1Score(Players.getPlayer1Score()+1);
                        player1Score.setText("Score: " + Players.getPlayer1Score());
                        Board.wins.get(i-1).setTextColor(player1Text.getCurrentTextColor());
                    } else {
                        Players.setPlayer2Score(Players.getPlayer2Score()+1);
                        player2Score.setText("Score: " + Players.getPlayer2Score());
                        Board.wins.get(i-1).setTextColor(player2Text.getCurrentTextColor());
                    }
                    thereWasAWinner = true;

                }
            }


        }
        return thereWasAWinner;
    }
    @SuppressLint("SetTextI18n")
    public void clearBoard(){
        Board.currLine = null;
        Board.clear();
        Players.clear();
        if(Players.getTurn() %2 == 0) {
            player1Turn.setVisibility(View.VISIBLE);
            player2Turn.setVisibility(View.INVISIBLE);
        }
        else {
            player1Turn.setVisibility(View.INVISIBLE);
            player2Turn.setVisibility(View.VISIBLE);
        }
        player1Score.setText("Score: " + Players.getPlayer1Score());
        player2Score.setText("Score: " + Players.getPlayer2Score());
        Resources r = getResources();
        String name = getPackageName();
        int l = Board.getNumOflines();
        int numOfWins = Board.getTotalWins();
        for (int i = 1; i <= l; i++) {
                View line = findViewById(r.getIdentifier("line" + i + Board.getGraphType(), "id", name));
                ViewGroup.LayoutParams params = line.getLayoutParams();
                if (params.height <= params.width)
                    params.height = PADDING;
                else
                    params.width = PADDING;
                line.setBackgroundColor(findViewById(R.id.square).getSolidColor());
                line.setLayoutParams(params);
                line.setOnClickListener(this);
        }
        for (int i = 1; i <= numOfWins; i++)
        {
            TextView x = findViewById(r.getIdentifier("win"+i+Board.getGraphType(), "id", name));
            x.setTextColor(findViewById(R.id.square).getSolidColor());
            Board.wins.add(x);
        }
    }
    public void openStartScreen(){
        Players.clear();
        Intent intent = new Intent(this, StartScreenActivity.class);
        startActivity(intent);
    }


}