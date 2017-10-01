package com.whatever.ghosts.ghosts_n_whatever;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.whatever.ghosts.fragments.QRScanner;
import com.whatever.ghosts.model.Backpack;
import com.whatever.ghosts.model.Character;
import com.whatever.ghosts.model.Game;
import com.whatever.ghosts.model.Item;
import com.whatever.ghosts.model.Location;

import java.util.ArrayList;
import java.util.HashMap;

public class ActiveGame extends AppCompatActivity implements QRScanner.IQRReadValue {
    final String TAG = "ActiveGame";

    TextView tvPlayerName, tvCharacterRole, tvScore, tvTimeLeft, tvItem;

    Character character;

    Game game;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Games").child(MyApp.gameID).child("Players").child(MyApp.playerID);
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
                try {
                    game = (dataSnapshot.getValue(Game.class));
                    tvTimeLeft.setText("" + game.getGameTime());
                    tvItem.setText(game.getCharacters().get(MyApp.playerID).getBackpack().toString());
                } catch (Exception ex){
                    Log.e(TAG, "Reciving Game failed!" +ex.getMessage());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    character = dataSnapshot.getValue(Character.class);
                    if (character.getBackpack() == null) { //TODO: Fix this on the website, so it create all the values connectly
                        character.setBackpack(new Backpack());
                        character.getBackpack().setItems(new ArrayList<Item>());
                    }

                    tvPlayerName.setText(character.getName());
                    tvCharacterRole.setText(character.getCharacterClass().toString());
                    tvScore.setText("" + character.getScore());
                    if (character.getBackpack().getItems().size() > 0) {
                        tvItem.setText(character.getBackpack().getItems().get(0).getName());
                    } else {
                        tvItem.setText("Empty");
                    }
                } catch (Exception ex){
                    Log.e(TAG, "Getting Character failed! " + ex.getMessage());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    @Override
    public void readValue(String text) {
        HashMap<String, Location> hashVillage = game.getLocations().get("Village");
        HashMap<String, Location> hashCrypt = game.getLocations().get("Crypt");

        character.setCurrentLocation(findLocation(hashVillage, hashCrypt, text));
        character.getCurrentLocation().Arriving(character);
    }

    Location findLocation(HashMap<String, Location> villages, HashMap<String, Location> crypts, String checkValue){
        Location somewhere = null;
        for (String k : villages.keySet()){
            Location l = villages.get(k);
            if (l.getName().equals(checkValue)){
                somewhere = l;
                somewhere.Key = k;
            }
        }
        for (String k : crypts.keySet()){
            Location l = crypts.get(k);
            if (l.getName().equals(checkValue)){
                somewhere = l;
                somewhere.Key = k;
            }
        }
        return somewhere;
    }

}


