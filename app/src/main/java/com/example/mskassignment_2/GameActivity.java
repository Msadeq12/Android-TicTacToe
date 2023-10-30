package com.example.mskassignment_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class GameActivity extends AppCompatActivity {

    private Drawable xPlayer;
    private Drawable oPlayer;

    private static int total_moves = 9;

    public static int moveCounter = 0;
    private ImageView[] boxes = new ImageView[9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        xPlayer = ResourcesCompat.getDrawable(getResources(), R.drawable.token_x, null);
        oPlayer = ResourcesCompat.getDrawable(getResources(), R.drawable.token_o, null);

    }

    public Drawable ShowPlayer(int count, ImageView image){

        if(count % 2 == 0){
            image.animate().translationYBy(50f);
            return xPlayer;
        }

        else{
            image.animate().translationYBy(50f);
            return oPlayer;
        }
    }

    /*
        Instantiates an ImageView at every click
     */
    public void playerTap(View view){
        moveCounter++;
        ImageView imgPlayer = (ImageView) view;
        int tappedBox = Integer.parseInt(imgPlayer.getTag().toString());

        imgPlayer.setTranslationY(-100f);

        imgPlayer.setImageDrawable(ShowPlayer(moveCounter, imgPlayer));



        Log.i("tappedBox", imgPlayer.getTag().toString());
    }
}