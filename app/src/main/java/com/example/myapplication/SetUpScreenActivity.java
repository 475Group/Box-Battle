package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class SetUpScreenActivity extends AppCompatActivity {
    RadioGroup radioGroup1;
    RadioGroup radioGroup2;
    RadioButton radioButton1;
    RadioButton radioButton2;
    Button confirmButton;
    EditText inputTextP1;
    EditText inputTextP2;
    String s1;
    String s2;
    int player1Color;
    int player2Color;



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
        setContentView(R.layout.activity_setup_screen);

        inputTextP1 = (EditText) findViewById(R.id.inputTextP1);
        inputTextP2 = (EditText) findViewById(R.id.inputTextP2);
        radioGroup1 = findViewById(R.id.radioGroup1);
        radioGroup2 = findViewById(R.id.radioGroup2);
        confirmButton = findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetUpScreenActivity.this, MainActivity.class);
                s1 = inputTextP1.getText().toString();
                s2 = inputTextP2.getText().toString();
                intent.putExtra("P1", s1);
                intent.putExtra("P2", s2);
               radioButton1 = findViewById(radioGroup1.getCheckedRadioButtonId());
               player1Color = radioButton1.getCurrentTextColor();
                radioButton2 = findViewById(radioGroup2.getCheckedRadioButtonId());
                player2Color = radioButton2.getCurrentTextColor();
                intent.putExtra("colorP1", player1Color);
                intent.putExtra("colorP2", player2Color);
                startActivity(intent);
                finish();

            }
        });


    }







}


