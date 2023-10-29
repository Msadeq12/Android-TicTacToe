package com.example.mskassignment_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView playerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerName = findViewById(R.id.txtName);
    }

    public void StartGame(View v){
        Intent startGame = new Intent(this, GameActivity.class);

        startGame.putExtra("player_name", playerName.toString());
        startActivity(startGame);

    }
}