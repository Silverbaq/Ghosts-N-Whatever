package com.whatever.ghosts.ghosts_n_whatever;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.whatever.ghosts.model.Character;
import com.whatever.ghosts.model.Game;

import java.util.HashMap;

public class JoinGame extends AppCompatActivity {
    final String TAG = "JoinGame";
    Button btnJoin;
    EditText etGameCode, etPlayerName;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Games");

    HashMap<String, String> gameList = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_game);

        btnJoin = (Button) findViewById(R.id.activity_joingame_btnJoin);
        etGameCode = (EditText) findViewById(R.id.activity_joingame_etGameCode);
        etPlayerName = (EditText) findViewById(R.id.activity_joingame_etPlayerName);

        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String gameCode = etGameCode.getText().toString();
                String playerName = etPlayerName.getText().toString();

                Character character = new Character();
                character.setName(playerName);
                character.setScore(0);
                character.setFrozen(false);

                String gameKey = gameList.get(gameCode);
                MyApp.gameID = gameKey;

                DatabaseReference gameRef = database.getReference("Games").child(gameKey).child("Players").push();
                MyApp.playerID = gameRef.getKey();

                gameRef.setValue(character);

                Intent intent = new Intent(JoinGame.this, LobbyActivity.class);
                startActivity(intent);
            }
        });


        // Read from the database
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Game game = dataSnapshot.getValue(Game.class);
                gameList.put(game.getGameCode(), dataSnapshot.getKey());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });


    }
}
