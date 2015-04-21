package com.example.devanshi.pair_o_dice;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;


public class Player2 extends ActionBarActivity {
    private FrameLayout die1, die2;
    private Button roll, hold;
    private TextView RoundScore;
    private TextView player1score ,player2score;
    int currentRoundScore=0,rScore;
    int totalPlayer1score ,totalPlayer2score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player2);

        die1 = (FrameLayout) findViewById(R.id.die1);
        die2 = (FrameLayout) findViewById(R.id.die2);
        RoundScore=(TextView)findViewById(R.id.round);
        player1score=(TextView)findViewById(R.id.p1);
        player2score=(TextView)findViewById(R.id.p2);


        Intent intent = getIntent();
        totalPlayer1score = intent.getIntExtra("totalPlayer1score", totalPlayer1score);
        totalPlayer2score = intent.getIntExtra("totalPlayer2score:",totalPlayer2score);
        Toast.makeText(this, "The score is: " + totalPlayer1score, Toast.LENGTH_LONG).show();
        player1score.setText("P1: " + totalPlayer1score);
        player2score.setText("P2: " + totalPlayer2score);



        roll = (Button) findViewById(R.id.button);
        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollDice();

            }
        });

        hold = (Button)findViewById(R.id.hold);
        hold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                totalPlayer2score= currentRoundScore + totalPlayer2score;
                if(totalPlayer2score >= 100)
                {
                    AlertDialog alertDialog = new AlertDialog.Builder(Player2.this).create();
                    alertDialog.setTitle("Winnner !!");
                    alertDialog.setMessage("Congratulations Player2 has won !!!!");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();}
                else {
                    //player2score.setText("P2:" +totalPlayer2score);
                    //player1score.setText("P1:" +totalPlayer1score);
                    Intent intent = new Intent(Player2.this, MainActivity.class);
                    intent.putExtra("totalPlayer2score", totalPlayer2score);
                    intent.putExtra("totalPlayer1score", totalPlayer1score);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    startActivity(intent);
                }

            }



        });



    }

    //get two random ints between 1 and 6 inclusive
    public void rollDice() {
        int val1 = 1 + (int) (6 * Math.random());
        int val2 = 1 + (int) (6 * Math.random());
        setDie(val1, die1);
        setDie(val2, die2);

        rScore=val1+val2;

        if(val1==1 || val2==1){
            RoundScore.setText("Round:0");
            rScore=0;
            totalPlayer2score= rScore + totalPlayer2score;

            Intent intent = new Intent(Player2.this,MainActivity.class);
            intent.putExtra("totalPlayer2score",totalPlayer2score);
            intent.putExtra("totalPlayer1score",totalPlayer1score);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);



        }
        else{

            RoundScore.setText("Round:" +rScore);
            player2score.setText("P2:" +totalPlayer2score);
            player1score.setText("P1:" +totalPlayer1score);
            currentRoundScore=rScore+currentRoundScore;
        }

    }

    //set the appropriate picture for each die per int
    public void setDie(int value, FrameLayout layout) {
        Drawable pic = null;

        switch (value) {
            case 1:
                pic = getResources().getDrawable(R.drawable.die_face_1);
                break;
            case 2:
                pic = getResources().getDrawable(R.drawable.die_face_2);
                break;
            case 3:
                pic = getResources().getDrawable(R.drawable.die_face_3);
                break;
            case 4:

                pic = getResources().getDrawable(R.drawable.die_face_4);
                break;
            case 5:
                pic = getResources().getDrawable(R.drawable.die_face_5);
                break;
            case 6:
                pic = getResources().getDrawable(R.drawable.die_face_6);
                break;
        }
        layout.setBackground(pic);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
/*
* AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
alertDialog.setTitle("Alert");
alertDialog.setMessage("Alert message to be shown");
alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
    new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
        }
    });
alertDialog.show();*/