package com.example.mskassignment_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    private Drawable xPlayer;
    private Drawable oPlayer;

    private static int total_moves = 9;

    public static int moveCounter = 0;
    public Drawable[] boxes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        xPlayer = ResourcesCompat.getDrawable(getResources(), R.drawable.token_black, null);
        oPlayer = ResourcesCompat.getDrawable(getResources(), R.drawable.token_white, null);

        boxes = new Drawable[9];

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


    }

    public void ResetGame(){

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
            imgPlayer.setImageDrawable(ShowPlayer(moveCounter, imgPlayer));


        }


    }
}