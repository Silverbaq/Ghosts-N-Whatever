package com.whatever.ghosts.ghosts_n_whatever;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class JoinGame extends AppCompatActivity {

    Button btnJoin;
    EditText etGameName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_game);

        btnJoin = (Button) findViewById(R.id.activity_joingame_btnJoin);
        etGameName = (EditText) findViewById(R.id.activity_joingame_etGameName);

        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String gameName = etGameName.getText().toString();
                // TODO : make code to join a game
            }
        });



    }
}
