package com.whatever.ghosts.model;

import java.util.List;

/**
 * Created by silverbaq on 29/09/2017.
 */

public class Character {

    public Location getHome() {
        return Home;
    }

    public void setHome(Location home) {
        Home = home;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }

    public boolean isFrozen() {
        return Frozen;
    }

    public void setFrozen(boolean frozen) {
        Frozen = frozen;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Location getCurrentLocation() {
        return CurrentLocation;
    }

    public void setCurrentLocation(Location currentLocation) {
        CurrentLocation = currentLocation;
    }

    public com.whatever.ghosts.model.Backpack getBackpack() {
        return Backpack;
    }

    public void setBackpack(com.whatever.ghosts.model.Backpack backpack) {
        Backpack = backpack;
    }

    public CharClass getCharacterClass() {
        return CharacterClass;
    }

    public void setCharacterClass(CharClass characterClass) {
        CharacterClass = characterClass;
    }

    public enum CharClass {
        Ghost, Hunter, Robber
    }

    private Location Home;
    private int Score;
    private boolean Frozen;
    private String Name;
    private Location CurrentLocation;
    private Backpack Backpack;
    private CharClass CharacterClass;


    public Character(){

    }



}
