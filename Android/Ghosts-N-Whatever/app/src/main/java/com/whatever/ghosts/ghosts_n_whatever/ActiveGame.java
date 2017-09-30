package com.whatever.ghosts.ghosts_n_whatever;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.whatever.ghosts.fragments.QRScanner;
import com.whatever.ghosts.model.Character;
import com.whatever.ghosts.model.Game;
import com.whatever.ghosts.model.Location;

public class ActiveGame extends AppCompatActivity implements QRScanner.IQRReadValue {
    final String TAG = "ActiveGame";

    TextView tvPlayerName, tvCharacterRole, tvScore, tvTimeLeft, tvItem;

    Character character;

    Game game;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Games").child(MyApp.gameID).child(MyApp.playerID);
    DatabaseReference gameRef = database.getReference("Games").child(MyApp.gameID);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_game);

        tvPlayerName = (TextView) findViewById(R.id.activity_game_name);
        tvCharacterRole = (TextView) findViewById(R.id.activity_game_role);
        tvScore = (TextView) findViewById(R.id.activity_game_score);
        tvTimeLeft = (TextView) findViewById(R.id.activity_game_timeleft);
        tvItem = (TextView) findViewById(R.id.activity_game_item);



        gameRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                game = (dataSnapshot.getValue(Game.class));
                tvTimeLeft.setText(game.GameTime);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                character = dataSnapshot.getValue(Character.class);

                tvPlayerName.setText(character.Name);
                tvCharacterRole.setText(character.CharacterClass.toString());
                tvScore.setText(character.Score);
                if (character.Backpack.getItems().size() > 0) {

                    tvItem.setText(character.Backpack.getItems().get(0).Name);
                }
                else{
                    tvItem.setText("Empty");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    @Override
    public void readValue(String text) {

        character.CurrentLocation = game.Locations.get(text);
        character.CurrentLocation.Arriving(character);
    }
}


