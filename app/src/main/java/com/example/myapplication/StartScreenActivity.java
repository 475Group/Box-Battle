package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class StartScreenActivity extends AppCompatActivity {
     Button button;


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

        setContentView(R.layout.activity_start_screen);


        button = findViewById(R.id.button);
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.monplaisir);   //Play music on after start button
        button.setOnClickListener(v -> {
            openMainActivity();
                mp.start();
                finish();

        });


    }

    public void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}