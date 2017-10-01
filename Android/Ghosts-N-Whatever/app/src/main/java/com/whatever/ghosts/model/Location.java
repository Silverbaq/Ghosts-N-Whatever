package com.whatever.ghosts.model;

import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.whatever.ghosts.ghosts_n_whatever.MyApp;

/**
 * Created by silverbaq on 29/09/2017.
 */

public class Location {

    final String TAG = "Location";

    private String Name;
    private Boolean Village;
    private Boolean Traped;
    private String HunterID;
    public String Key;

    public Location() {

    }

    public void Arriving(Character player) {
        if (this.getVillage()) {
            switch (player.getCharacterClass()) {
                case Ghost:
                    Item item1 = new Item();
                    item1.setName("Soul");
                    item1.setValue(10);
                    player.getBackpack().addItem(item1);
                    Log.d(TAG, "Ghost added soul");

                    break;
                case Robber:
                    int value = player.getBackpack().UseItem();
                    player.setScore(player.getScore() + value);
                    break;
                case Hunter:
                    Item item3 = new Item();
                    item3.setName("Trap");
                    item3.setValue(10);
                    player.setScore(player.getScore() - 10);
                    player.getBackpack().addItem(item3);
                    break;
            }
        } else {
            //Crypt crypt = (Crypt)this;
            switch (player.getCharacterClass()) {
                case Ghost:
                    if(getTraped() != null && getTraped()){
                        player.setScore(player.getScore() - 5);
                        player.getBackpack().UseItem();
                        setTraped(false);
                        // TODO: give hunter points
                        giveHunterPoints(25);
                    } else {
                        int value = player.getBackpack().UseItem();
                        player.setScore(player.getScore() + value);
                    }
                    break;
                case Hunter:
                    if (getTraped() == null || !getTraped()) {
                        if (player.getBackpack().getItems().size() > 0) {
                            int points = player.getBackpack().UseItem();

                            setTraped(true);
                            setHunterID(MyApp.playerID);
                            MyApp.AddUpdateLocation(Location.this);
                        }
                    } else if (getTraped() == null){
                        // TODO : add trap and save updated location on firebase

                    }
                    break;
                case Robber:
                    if (getTraped() != null && getTraped()){
                        if (player.getBackpack().getItems().size() < 0){
                            setTraped(false);
                            MyApp.AddUpdateLocation(Location.this);
                            Item item = new Item();
                            item.setName("Trap");
                            item.setValue(25);
                            player.getBackpack().addItem(item);
                        }
                    }
                    break;
            }
        }
        //FirebaseDatabase database = FirebaseDatabase.getInstance();
        //DatabaseReference myRef = database.getReference("Games").child(MyApp.gameID).child("Players").child(MyApp.playerID);
        //myRef.setValue(player);

        MyApp.AddUpdateCharacter(player);
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Boolean getVillage() {
        return Village;
    }

    public void setVillage(Boolean village) {
        Village = village;
    }

    public Boolean getTraped() {
        return Traped;
    }

    public void setTraped(Boolean traped) {
        Traped = traped;
    }

    public String getHunterID() {
        return HunterID;
    }

    public void setHunterID(String hunterID) {
        HunterID = hunterID;
    }

    private void giveHunterPoints(final int points){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Games").child(MyApp.gameID).child("Players").child(getHunterID());
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    Character character = dataSnapshot.getValue(Character.class);
                    character.setScore(character.getScore() + points);
                    MyApp.AddUpdateCharacter(character);
                } catch (Exception ex){
                    Log.e(TAG, "Giving hunter points failed!: " + ex.getMessage());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
