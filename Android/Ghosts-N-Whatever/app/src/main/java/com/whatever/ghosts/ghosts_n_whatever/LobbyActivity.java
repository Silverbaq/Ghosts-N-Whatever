package com.whatever.ghosts.ghosts_n_whatever;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.whatever.ghosts.model.Character;
import com.whatever.ghosts.model.Game;

public class LobbyActivity extends AppCompatActivity {

    Button btnAddLocation;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Games").child(MyApp.gameID).child("GameState");


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

        myRef.addValueEventListener(myStateEvent);
    }

    ValueEventListener myStateEvent = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            String state = dataSnapshot.getValue(String.class);
            if (state.equals("Running")){
                Intent intent = new Intent(LobbyActivity.this, ActiveGame.class);
                startActivity(intent);
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

}
