package com.wadhavekar.rockpaperscissor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FinalActivity extends AppCompatActivity {
    SharedPreferences sp;
    TextView result;
    Button replay;
    private static final String WINNER = "Winner";
    private static final String SHARED_PREFS = "RPS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
        result = findViewById(R.id.tv_myResult);
        replay = findViewById(R.id.button_playAgain);

        sp = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        result.setText(sp.getString(WINNER,"null"));

        replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FinalActivity.this,InitialScreen.class);
                startActivity(intent);
                finish();
            }
        });


    }
}
