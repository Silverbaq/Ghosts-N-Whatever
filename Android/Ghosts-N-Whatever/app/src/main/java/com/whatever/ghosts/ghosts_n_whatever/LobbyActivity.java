package com.whatever.ghosts.ghosts_n_whatever;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LobbyActivity extends AppCompatActivity {

    Button btnAddLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);


        btnAddLocation = (Button) findViewById(R.id.activity_Lobby_btnAddLocation);
        btnAddLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LobbyActivity.this, AddLocationActivity.class);
                startActivity(intent);
            }
        });

    }
}
