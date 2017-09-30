package com.whatever.ghosts.model;

import java.util.List;

/**
 * Created by silverbaq on 29/09/2017.
 */

public abstract class Character {


    public List<Location> Locations;
    public Location Home;
    public int Score;
    public boolean Frozen;
    public String Name;
    public Location CurrentLocation;
    public Backpack Backpack;

    public Character(){

    }

}
