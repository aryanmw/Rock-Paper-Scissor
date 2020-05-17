package com.wadhavekar.rockpaperscissor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView rockPlayer1,rockPlayer2,paperPlayer1,paperPlayer2,scissorPlayer1,scissorPlayer2;
    RelativeLayout player1,player2;
    int p1Val,p2Val,numberOfRounds,scoreP1,scoreP2,click=0;
    Button goButton,nextRound;
    TextView p1Name,p2Name,resultDeclaration,score,tv_round;


    private static final String PLAYER1 = "Name of Player 1";
    private static final String PLAYER2 = "Name of Player 2";
    private static final String PLAYERS = "Number of players";
    private static final String ROUNDS = "Number of rounds";
    private static final String WINNER = "Winner";
    private static final String SHARED_PREFS = "RPS";

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rockPlayer1 = findViewById(R.id.iv_rock_p1);
        rockPlayer2 = findViewById(R.id.iv_rock_p2);
        paperPlayer1 = findViewById(R.id.iv_paper_p1);
        paperPlayer2 = findViewById(R.id.iv_paper_p2);
        scissorPlayer1 = findViewById(R.id.iv_scissor_p1);
        scissorPlayer2 = findViewById(R.id.iv_scissor_p2);
        player1 = findViewById(R.id.rel_player1);
        player2 = findViewById(R.id.rel_player2);
        goButton = findViewById(R.id.go_button);
        nextRound = findViewById(R.id.nextRound_button);
        p1Name = findViewById(R.id.tv_player1Name);
        p2Name = findViewById(R.id.tv_player2Name);
        resultDeclaration = findViewById(R.id.tv_result);
        score = findViewById(R.id.tv_score);
        tv_round = findViewById(R.id.tv_round);

        sp = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        final int numPlayers = sp.getInt(PLAYERS,1);
        final int rounds = sp.getInt(ROUNDS,1);

        tv_round.setText("Round 1");


        if (numPlayers == 2) {
            p1Name.setText(""+sp.getString(PLAYER1,"Player 1"));
            p2Name.setText(""+sp.getString(PLAYER2,"Player 2"));
            rockPlayer1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    p1Val = 1;
                    makeInvisiblePlayer1();

                    if (click ==1){
                        goButton.setVisibility(View.VISIBLE);
                    }

                    click++;

                }
            });
            paperPlayer1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    p1Val=2;
                    makeInvisiblePlayer1();
                    if (click ==1){
                        goButton.setVisibility(View.VISIBLE);
                    }
                    click++;
                }
            });
            scissorPlayer1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    p1Val = 3;
                    makeInvisiblePlayer1();
                    if (click ==1){
                        goButton.setVisibility(View.VISIBLE);
                    }
                    click++;
                    //Toast.makeText(MainActivity.this, ""+p1Val+" "+p2Val, Toast.LENGTH_SHORT).show();
                }
            });

            rockPlayer2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    p2Val=1;
                    makeInvisiblePlayer2();
                    if (click ==1){
                        goButton.setVisibility(View.VISIBLE);
                    }
                    click++;
                }
            });

            paperPlayer2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    p2Val=2;
                    makeInvisiblePlayer2();
                    if (click ==1){
                        goButton.setVisibility(View.VISIBLE);
                    }
                    click++;
                }
            });
            scissorPlayer2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    p2Val=3;
                    makeInvisiblePlayer2();
                    if (click ==1){
                        goButton.setVisibility(View.VISIBLE);
                    }
                    click++;
                }
            });
        }

        else {
            p2Name.setText(""+sp.getString(PLAYER1,"Player 1"));
            p1Name.setText("Computer");
            p2Val = (int)(Math.random()%3) + 1;
            if (p2Val < 0){
                p2Val = p2Val * (-1);
            }

            rockPlayer2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    p1Val=1;
                    makeInvisiblePlayer1();
                    makeInvisiblePlayer2();

                    goButton.setVisibility(View.VISIBLE);

                }
            });

            paperPlayer2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    p1Val=2;
                    makeInvisiblePlayer1();
                    makeInvisiblePlayer2();

                    goButton.setVisibility(View.VISIBLE);

                }
            });
            scissorPlayer2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    p1Val=3;
                    makeInvisiblePlayer1();
                    makeInvisiblePlayer2();

                    goButton.setVisibility(View.VISIBLE);

                }
            });
        }


        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultDeclaration.setVisibility(View.VISIBLE);
                if (p1Val == p2Val){
                    resultDeclaration.setText("It is a tie");
                }
                else if ((p1Val == 1 && p2Val == 3) || (p1Val == 2 && p2Val == 1) || (p1Val == 3 && p2Val == 2)){
                    if (numPlayers == 2) {
                        player1BackgroundChange();
                    } else {
                        player2BackgroundChange();
                    }

                    resultDeclaration.setText( sp.getString(PLAYER1,"Player 1")+" wins this round ");


                    scoreP1++;
                }
                else if ((p2Val == 1 && p1Val == 3) || (p2Val == 2 && p1Val == 1) || (p2Val == 3 && p1Val == 2)){

                    if (numPlayers == 2) {
                        resultDeclaration.setText( sp.getString(PLAYER2,"Player 1")+" wins this round ");
                        player2BackgroundChange();
                    }
                    else{
                        resultDeclaration.setText("Computer wins this round");
                        player1BackgroundChange();
                    }

                    scoreP2++;
                }
                if (numPlayers == 1) {
                    score.setText( sp.getString(PLAYER1,"Player 1")+" : "+scoreP1 +" and Computer : "+ scoreP2);
                }
                else{
                    score.setText( sp.getString(PLAYER1,"Player 1")+" : "+scoreP1 +" and " + sp.getString(PLAYER2,"Player 2") + " : "+ scoreP2);
                }

                goButton.setVisibility(View.INVISIBLE);
                nextRound.setVisibility(View.VISIBLE);
                if (rounds - 1 == numberOfRounds){
                     nextRound.setText("End Game");
                }


            }
        });

        nextRound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberOfRounds++;
                nextRound.setVisibility(View.INVISIBLE);
                score.setText("");
                resultDeclaration.setText("");
                makeVisiblePlayer1();
                makeVisiblePlayer2();
                click = 0;
                normalBackgroundChange();
                tv_round.setText("Round "+(numberOfRounds+1));
                if (numberOfRounds == rounds){
                    if (scoreP1 > scoreP2){
                        saveWinner("Congratulations, "+sp.getString(PLAYER1,"Player 1")+ ", you won the game");
                    }
                    else if (scoreP2>scoreP1){
                        if (numPlayers == 1){
                            saveWinner("Better luck next time "+sp.getString(PLAYER1,"Player 1"));
                        }
                        else{
                            saveWinner("Congratulations, "+sp.getString(PLAYER2,"Player 2")+ ", you won the game");
                        }
                    }
                    else{
                        saveWinner("You both tied. Play another game to find the real winner");
                    }
                    Intent intent = new Intent(MainActivity.this,FinalActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });






    }

    private void makeInvisiblePlayer1(){
        scissorPlayer1.setVisibility(View.INVISIBLE);
        rockPlayer1.setVisibility(View.INVISIBLE);
        paperPlayer1.setVisibility(View.INVISIBLE);
        p1Name.setVisibility(View.INVISIBLE);
    }
    private void makeInvisiblePlayer2(){
        scissorPlayer2.setVisibility(View.INVISIBLE);
        rockPlayer2.setVisibility(View.INVISIBLE);
        paperPlayer2.setVisibility(View.INVISIBLE);
        p2Name.setVisibility(View.INVISIBLE);
    }
    private void makeVisiblePlayer1(){
        scissorPlayer1.setVisibility(View.VISIBLE);
        rockPlayer1.setVisibility(View.VISIBLE);
        paperPlayer1.setVisibility(View.VISIBLE);
        p1Name.setVisibility(View.VISIBLE);

    }
    private void makeVisiblePlayer2(){
        scissorPlayer2.setVisibility(View.VISIBLE);
        rockPlayer2.setVisibility(View.VISIBLE);
        paperPlayer2.setVisibility(View.VISIBLE);
        p2Name.setVisibility(View.VISIBLE);
    }
    private void player1BackgroundChange(){
        player1.setBackgroundColor(Color.parseColor("#4CAF50"));
        player2.setBackgroundColor(Color.parseColor("#F71908"));

    }
    private void player2BackgroundChange(){
        player2.setBackgroundColor(Color.parseColor("#4CAF50"));
        player1.setBackgroundColor(Color.parseColor("#F71908"));

    }
    private void normalBackgroundChange(){
        player1.setBackgroundColor(Color.parseColor("#FFC107"));
        player2.setBackgroundColor(Color.parseColor("#03A9F4"));
    }

    public void saveWinner(String name){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(WINNER,name);
        editor.apply();
        //Log.i("SP","??????????"+sharedPreferences);
    }

}
