package com.whatever.ghosts.ghosts_n_whatever;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CreateGame extends AppCompatActivity {

    EditText etGameCode, etGameTime;
    TextView tvVillages, tvCrypts;
    Button btnCreateGame,  btnAddLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_game);

        etGameCode = (EditText) findViewById(R.id.activity_createGame_GameCodeValue);
        etGameTime = (EditText) findViewById(R.id.activity_createGame_GameTime);
        tvVillages = (TextView) findViewById(R.id.activity_createGame_tvVillages);
        tvCrypts = (TextView) findViewById(R.id.activity_createGame_tvCrypts);
        btnCreateGame = (Button) findViewById(R.id.activity_createGame_btnStartGame);
        btnAddLocation = (Button) findViewById(R.id.activity_createGame_btnAddLocation);

        btnAddLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateGame.this, AddLocationActivity.class);
                startActivity(intent);
            }
        });

        btnCreateGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateGame.this, ActiveGame.class);
                startActivity(intent);
            }
        });

    }
}
