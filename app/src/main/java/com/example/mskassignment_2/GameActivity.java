package com.example.mskassignment_2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;



public class GameActivity extends AppCompatActivity {

    Drawable player1;
    Drawable player2;

    String player1Name;
    String player2Name;

    ImageView image1;

    ImageView image2;

    ImageView image3;

    ImageView image4;

    ImageView image5;

    ImageView image6;

    ImageView image7;

    ImageView image8;

    ImageView image9;


    int total_moves = 9;

    int moveCounter = 0;
    public Drawable[] boxes;

    TextView gameMessage;
    Button playAgain;
    Button goHome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        player1 = ResourcesCompat.getDrawable(getResources(), R.drawable.token_black, null);
        player2 = ResourcesCompat.getDrawable(getResources(), R.drawable.token_white, null);

        player1Name = getIntent().getStringExtra("player1_name");
        player2Name = getIntent().getStringExtra("player2_name");

        boxes = new Drawable[9];

        gameMessage = findViewById(R.id.txtGameMessage);

        playAgain = findViewById(R.id.btnPlayAgain);
        playAgain.setVisibility(View.INVISIBLE);

        goHome = findViewById(R.id.btnHome);
        goHome.setVisibility(View.INVISIBLE);

        image1 = findViewById(R.id.imgBox0);
        image2 = findViewById(R.id.imgBox1);
        image3 = findViewById(R.id.imgBox2);
        image4 = findViewById(R.id.imgBox3);
        image5 = findViewById(R.id.imgBox4);
        image6 = findViewById(R.id.imgBox5);
        image7 = findViewById(R.id.imgBox6);
        image8 = findViewById(R.id.imgBox7);
        image9 = findViewById(R.id.imgBox8);



    }



    public Drawable ShowPlayer(int count, ImageView image){

        if(count % 2 == 0){
            image.setTranslationY(-1000f);
            image.animate().translationYBy(1000f);
            return player1;
        }

        else{
            image.setTranslationY(-1000f);
            image.animate().translationYBy(1000f);
            return player2;
        }
    }

    /*
        Conditions for winning and losing
     */
    public void WinLoseConditions(){
        Drawable combo1 = null;
        Drawable combo2 = null;
        Drawable combo3 = null;

        for(int i = 0; i < total_moves; i++){

            switch(i){
                case 0:
                    combo1 = boxes[0];
                    combo2 = boxes[4];
                    combo3 = boxes[8];
                    break;

                case 1:
                    combo1 = boxes[2];
                    combo2 = boxes[4];
                    combo3 = boxes[6];
                    break;

                case 2:
                    combo1 = boxes[0];
                    combo2 = boxes[1];
                    combo3 = boxes[2];
                    break;

                case 3:
                    combo1 = boxes[3];
                    combo2 = boxes[4];
                    combo3 = boxes[5];
                    break;

                case 4:
                    combo1 = boxes[6];
                    combo2 = boxes[7];
                    combo3 = boxes[8];
                    break;

                case 5:
                    combo1 = boxes[0];
                    combo2 = boxes[3];
                    combo3 = boxes[6];
                    break;

                case 6:
                    combo1 = boxes[1];
                    combo2 = boxes[4];
                    combo3 = boxes[7];
                    break;

                case 7:
                    combo1 = boxes[2];
                    combo2 = boxes[5];
                    combo3 = boxes[8];
                    break;

            }



            if(combo1 == player2 && combo2 == player2 && combo3 == player2){
                gameMessage.setText(player2Name.concat(" has won!"));
                playAgain.setVisibility(View.VISIBLE);
                goHome.setVisibility(View.VISIBLE);
                ClickImageFalse();

            }

            else if(combo1 == player1 && combo2 == player1 && combo3 == player1){
                gameMessage.setText(player1Name.concat(" has won!"));
                playAgain.setVisibility(View.VISIBLE);
                goHome.setVisibility(View.VISIBLE);
                ClickImageFalse();

            }


        }

    }

    // Sets everything to zero, null
    public void ResetGame(){
        moveCounter = 0;

        image1.setImageDrawable(null);
        image2.setImageDrawable(null);
        image3.setImageDrawable(null);
        image4.setImageDrawable(null);
        image5.setImageDrawable(null);
        image6.setImageDrawable(null);
        image7.setImageDrawable(null);
        image8.setImageDrawable(null);
        image9.setImageDrawable(null);



        Arrays.fill(boxes, null);
    }

    // disables the click event for imageviews
    public void ClickImageFalse(){
        image1.setClickable(false);
        image2.setClickable(false);
        image3.setClickable(false);
        image4.setClickable(false);
        image5.setClickable(false);
        image6.setClickable(false);
        image7.setClickable(false);
        image8.setClickable(false);
        image9.setClickable(false);
    }

    // re-enables the click event for imageviews
    public void ClickImageTrue(){
        image1.setClickable(true);
        image2.setClickable(true);
        image3.setClickable(true);
        image4.setClickable(true);
        image5.setClickable(true);
        image6.setClickable(true);
        image7.setClickable(true);
        image8.setClickable(true);
        image9.setClickable(true);
    }



    // Resets everything and hides the buttons for the player to play again
    public void PlayAgain(View view){
        ResetGame();
        playAgain.setVisibility(View.INVISIBLE);
        goHome.setVisibility(View.INVISIBLE);
        gameMessage.setText("");
        ClickImageTrue();
    }

    // The Home Button sends the player back to the Main Activity
    public void GoHome(View view){
        Intent goHome = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(goHome);
        ResetGame();
    }

    /*
        Assigns a player token to ImageView at every click
     */
    public void playerTap(View view){
        ImageView imgPlayer = (ImageView) view;

        if(imgPlayer.getDrawable() == null){
            moveCounter++;
            int tappedBox = Integer.parseInt(imgPlayer.getTag().toString());

            boxes[tappedBox] = ShowPlayer(moveCounter, imgPlayer);
            imgPlayer.setImageDrawable(boxes[tappedBox]);
            WinLoseConditions();

            // if neither player has won, the game is a tie
            if(moveCounter == total_moves && gameMessage.getText().toString() == ""){
                gameMessage.setText("It is a tie!");
                playAgain.setVisibility(View.VISIBLE);
                goHome.setVisibility(View.VISIBLE);
                ClickImageFalse();
            }

        }
    }



}