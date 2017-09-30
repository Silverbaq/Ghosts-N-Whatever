package com.whatever.ghosts.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.whatever.ghosts.ghosts_n_whatever.MyApp;

/**
 * Created by silverbaq on 29/09/2017.
 */

public class Location {

    private String Name;
    private Boolean Village;
    private Boolean Traped;
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
                    int value = player.getBackpack().UseItem();
                    player.setScore(player.getScore() + value);
                    break;
                case Hunter:
                    if (!getTraped()) {
                        if (player.getBackpack().getItems().size() > 0) {
                            int points = player.getBackpack().UseItem();
                            setTraped(true);

                        }
                    }
                    break;
                case Robber:

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
}
