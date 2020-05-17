package com.wadhavekar.rockpaperscissor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NameGetter extends AppCompatActivity {
    private static final String SHARED_PREFS = "RPS";
    private static final String PLAYER1 = "Name of Player 1";
    private static final String PLAYER2 = "Name of Player 2";
    private static final String PLAYERS = "Number of players";
    Button done;
    EditText et_p1,et_p2;
    TextView instruction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_getter);
        et_p1 = findViewById(R.id.et_player1Name);
        et_p2 = findViewById(R.id.et_player2Name);
        done = findViewById(R.id.button_done);
        instruction = findViewById(R.id.tv_player2);

        SharedPreferences sp = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        int p = sp.getInt(PLAYERS,1);
        if (p == 1){
            done.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (et_p1.getText().toString() == ""){
                        Toast.makeText(NameGetter.this, "Please enter your name", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        savePlayer1Name(et_p1.getText().toString());
                        Intent intent = new Intent(NameGetter.this,MainActivity.class);
                        startActivity(intent);
                    }
                }
            });
        }
        else{
            et_p2.setVisibility(View.VISIBLE);
            instruction.setVisibility(View.VISIBLE);
            done.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (et_p1.getText().toString() == "" || et_p2.getText().toString() == ""){
                        Toast.makeText(NameGetter.this, "Please enter your name", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        savePlayer1Name(et_p1.getText().toString());
                        savePlayer2Name(et_p2.getText().toString());
                        Intent intent = new Intent(NameGetter.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            });
        }



    }
    public void savePlayer1Name(String name){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PLAYER1,name);
        editor.apply();
        //Log.i("SP","??????????"+sharedPreferences);
    }
    public void savePlayer2Name(String name){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PLAYER2,name);
        editor.apply();
        //Log.i("SP","??????????"+sharedPreferences);
    }
}
