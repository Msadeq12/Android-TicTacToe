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

public class GameActivity extends AppCompatActivity {

    Drawable xPlayer;
    Drawable oPlayer;

    private static int total_moves = 9;

    public static int moveCounter = 0;
    public Drawable[] boxes;

    TextView gameMessage;
    Button playAgain;
    Button goHome;

    AlertDialog.Builder resultBuilder;
    AlertDialog resultGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        xPlayer = ResourcesCompat.getDrawable(getResources(), R.drawable.token_black, null);
        oPlayer = ResourcesCompat.getDrawable(getResources(), R.drawable.token_white, null);

        boxes = new Drawable[9];
        gameMessage = findViewById(R.id.txtGameMessage);
        playAgain = findViewById(R.id.btnPlayAgain);
        goHome = findViewById(R.id.btnHome);




    }



    public Drawable ShowPlayer(int count, ImageView image){

        if(count % 2 == 0){
            image.setTranslationY(-1000f);
            image.animate().translationYBy(1000f);
            return xPlayer;
        }

        else{
            image.setTranslationY(-1000f);
            image.animate().translationYBy(1000f);
            return oPlayer;
        }
    }

    /*
        Conditions for winning, losing, and a tie
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

                default:
                    break;
            }

            resultBuilder = new AlertDialog.Builder(GameActivity.this);
            resultBuilder.setTitle("Game has concluded!");
            resultBuilder.setCancelable(false);
            resultBuilder.setPositiveButton("Play again", (DialogInterface.OnClickListener) (dialog, which) ->{
                dialog.cancel();
            });

            resultBuilder.setNegativeButton("Home", (DialogInterface.OnClickListener) (dialog, which) ->{
                Intent goHome = new Intent(this, MainActivity.class);
                startActivity(goHome);
            });



            if(combo1 == oPlayer && combo2 == oPlayer && combo3 == oPlayer){
                gameMessage.setText("O Player has won.");
                resultBuilder.setMessage("O Player has won.");
                resultGame = resultBuilder.create();
                resultGame.show();

            }

            else if(combo1 == xPlayer && combo2 == xPlayer && combo3 == xPlayer){
                gameMessage.setText("X Player has won.");
                resultBuilder.setMessage("X Player has won.");
                resultGame = resultBuilder.create();
                resultGame.show();
            }

            else if(moveCounter == 9){
                gameMessage.setText("It's a tie.");
                resultBuilder.setMessage("It's a tie");
                resultGame = resultBuilder.create();
                resultGame.show();
            }

        }

    }

    public void ResetGame(){

    }

    /*
        Assigns a player token to ImageView at every click
     */
    public void playerTap1(View view){
        ImageView imgPlayer = (ImageView) view;

        if(imgPlayer.getDrawable() == null){
            moveCounter++;
            int tappedBox = Integer.parseInt(imgPlayer.getTag().toString());
            Log.i("tapped", imgPlayer.getTag().toString());

            boxes[tappedBox] = ShowPlayer(moveCounter, imgPlayer);
            imgPlayer.setImageDrawable(boxes[tappedBox]);
            WinLoseConditions();
        }
    }

}