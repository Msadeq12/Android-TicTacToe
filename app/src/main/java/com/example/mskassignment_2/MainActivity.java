package com.example.mskassignment_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText player1Name;
    EditText player2Name;
    Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player1Name = findViewById(R.id.txtPlayer1);
        player1Name.addTextChangedListener(watchPlayerNames);

        player2Name = findViewById(R.id.txtPlayer2);
        player2Name.addTextChangedListener(watchPlayerNames);

        startButton = findViewById(R.id.btnStart);
        startButton.setEnabled(false);

    }


    TextWatcher watchPlayerNames = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            startButton.setEnabled(!player2Name.getText().toString().isEmpty() || !player1Name.getText().toString().isEmpty());
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };


    public void StartGame(View v){
        Intent startGame = new Intent(this, GameActivity.class);

        startButton.setEnabled(true);
        startGame.putExtra("player1_name", player1Name.getText().toString());
        startGame.putExtra("player2_name", player2Name.getText().toString());
        startActivity(startGame);


    }
}