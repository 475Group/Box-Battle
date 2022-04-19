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
    //text palyer name and score shown on page
    TextView player1Score, player2Score, player1Text, player2Text, player1Turn, player2Turn;
    //String player1Name, player2Name;
    //int player1Color, player2Color;
    ImageView avatarP1, avatarP2;
    //String player1Avatar, player2Avatar;
    //int imageResource1, imageResource2;
    //initalize scores to 0
    int player1ScoreValue = 0, player2ScoreValue = 0;
    //no line selected yet so previous line is null
    View currLine = null;
    //first player starts game
    int turn = 0;
    //stores all lines selected in game
    ArrayList<View> lines = new ArrayList<>();
    //stores all possible wins in game
    ArrayList<TextView> wins = new ArrayList<>();

    /*public void setUpPlayerInfo(){
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


    }*/

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
        /*player1Name = getIntent().getExtras().getString("P1");
        player1Color = getIntent().getIntExtra("colorP1", -16777216);
        player2Name = getIntent().getExtras().getString("P2");
        player2Color = getIntent().getIntExtra("colorP2", -16777216);
        player1Avatar = "@drawable/" + getIntent().getExtras().getString("avatarP1");
        player2Avatar = "@drawable/" + getIntent().getExtras().getString("avatarP2");
        avatarP1 = findViewById(R.id.avatarP1);
        avatarP2 = findViewById(R.id.avatarP2);*/
        player1Score = findViewById(R.id.player1Score);
        player1Score.setTextColor(Players.getPlayer1Color());

        player2Score = findViewById(R.id.player2Score);
        player2Score.setTextColor(Players.getPlayer2Color());

        player1Turn = findViewById(R.id.Player1Turn);
        player2Turn = findViewById(R.id.Player2Turn);

        //setUpPlayerInfo();
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
                //finish();
                /*openInfoActivity();
                finish();*/
            }
        });

        //clear board on start
        clearBoard();

        //alert for quit
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
    public void onClick(View view) {
        if (!lines.contains(view)) {

            ViewGroup.LayoutParams params = view.getLayoutParams();
            int size = 20;
            if (params.width == 130)
                params.width = size;
            else
                params.height = size;
            view.setLayoutParams(params);
            view.setBackgroundColor(Color.BLACK);
            currLine = view;
            play();
        }

    }
    public void play(){
        //there must be a line selected
            //checks if current player got a win
            boolean stillLastPlayerTurn = checkWin();
            //checks if there was a win
            if (player2ScoreValue + player1ScoreValue == Board.getTotalWins())
            {
                //alerts players of winner
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                if (player1ScoreValue > player2ScoreValue) {
                    alertDialog.setTitle(player1Text.getText() + " Wins!");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "CANCEL",
                            (dialog, which) -> dialog.dismiss());
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "QUIT",
                            (dialog, which) -> openStartScreen());
                }
                else if (player1ScoreValue < player2ScoreValue) {
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
                //previousLine = null;
            }
            //otherwise switch players
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
                //set  prev line to null to show that no line has been selected
                //previousLine = null;
            }
        }
    //}
    @SuppressLint("SetTextI18n")
    public boolean checkWin(){
        lines.add(currLine);
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
            if (lines.contains(findViewById(r.getIdentifier("line" + firstCondition + graphType, "id", name))) &&
                    lines.contains(findViewById(r.getIdentifier("line" + secondCondition + graphType, "id", name)))&&
                    lines.contains(findViewById(r.getIdentifier("line" + thirdCondition + graphType, "id", name)))&&
                    lines.contains(findViewById(r.getIdentifier("line" + fourthCondition + graphType, "id", name)))){
                if(currLine.equals(findViewById(r.getIdentifier("line" + firstCondition + graphType, "id", name))) ||
                    currLine.equals(findViewById(r.getIdentifier("line" + secondCondition + graphType, "id", name))) ||
                        currLine.equals(findViewById(r.getIdentifier("line" + thirdCondition + graphType, "id", name)))||
                            currLine.equals(findViewById(r.getIdentifier("line" + fourthCondition + graphType, "id", name)))){

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
        player1Turn.setVisibility(View.VISIBLE);
        player2Turn.setVisibility(View.INVISIBLE);
        turn = 0;
        currLine = null;
        lines.clear();
        wins.clear();
        player1ScoreValue = 0;
        player2ScoreValue = 0;
        player1Score.setText("Score: " + player1ScoreValue);
        player2Score.setText("Score: " + player2ScoreValue);
        Resources r = getResources();
        String name = getPackageName();
        int l = Board.getNumOflines();
        int numOfWins = Board.getTotalWins();
        for (int i = 1; i <= l; i++) {
                View line = findViewById(r.getIdentifier("line" + i + Board.getGraphType(), "id", name));
                ViewGroup.LayoutParams params = line.getLayoutParams();
                if (params.height <= params.width)
                    params.height = 130;
                else
                    params.width = 130;
                line.setBackgroundColor(findViewById(R.id.square).getSolidColor());
                line.setLayoutParams(params);
                line.setOnClickListener(this);
        }
        for (int i = 1; i <= numOfWins; i++)
        {
            TextView x = findViewById(r.getIdentifier("win"+i+Board.getGraphType(), "id", name));
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