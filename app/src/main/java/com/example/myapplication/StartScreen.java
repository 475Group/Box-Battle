package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class StartScreen extends AppCompatActivity {
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
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openMainActivity();
                finish();

            }
        });


    }

    public void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}