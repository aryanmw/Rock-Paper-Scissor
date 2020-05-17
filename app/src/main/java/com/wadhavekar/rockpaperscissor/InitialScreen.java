package com.wadhavekar.rockpaperscissor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InitialScreen extends AppCompatActivity {
    Button rounds3,rounds5,rounds7,done,multiplayer,soloPlayer;
    TextView tv_players;
    private static final String SHARED_PREFS = "RPS";
    private static final String ROUNDS = "Number of rounds";
    private static final String PLAYERS = "Number of players";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_screen);
        rounds3 = findViewById(R.id.button_3x3);
        rounds5 = findViewById(R.id.button_5x5);
        rounds7 = findViewById(R.id.button_7x7);
        multiplayer = findViewById(R.id.multiplayer);
        soloPlayer = findViewById(R.id.soloPlayer);
        tv_players = findViewById(R.id.tv_numberOfPlayers);

        rounds3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNumberOfRounds(1);
                showNumberOfPlayers();

            }
        });
        rounds5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNumberOfRounds(3);
                showNumberOfPlayers();

            }
        });
        rounds7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNumberOfRounds(5);
                showNumberOfPlayers();

            }
        });

        soloPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNumberOfPlayers(1);
                Intent intent = new Intent(InitialScreen.this,NameGetter.class);
                startActivity(intent);
            }
        });
        multiplayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNumberOfPlayers(2);
                Intent intent = new Intent(InitialScreen.this,NameGetter.class);
                startActivity(intent);
            }
        });


    }
    public void saveNumberOfRounds(int num){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(ROUNDS,num);
        editor.apply();
        //Log.i("SP","??????????"+sharedPreferences);
    }
    public void saveNumberOfPlayers (int num){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(PLAYERS,num);
        editor.apply();
        //Log.i("SP","??????????"+sharedPreferences);
    }

    private void showNumberOfPlayers(){
        soloPlayer.setVisibility(View.VISIBLE);
        multiplayer.setVisibility(View.VISIBLE);
        tv_players.setVisibility(View.VISIBLE);

    }
}
