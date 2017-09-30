package com.whatever.ghosts.model;

import java.util.HashMap;
import java.util.List;

/**
 * Created by silverbaq on 29/09/2017.
 */

public class Game {

    public HashMap<String, Character> getCharacters() {
        return Characters;
    }

    public void setCharacters(HashMap<String, Character> characters) {
        Characters = characters;
    }

    public HashMap<String, HashMap<String, Location>> getLocations() {
        return Locations;
    }

    public void setLocations(HashMap<String, HashMap<String, Location>> locations) {
        Locations = locations;
    }

    public Game.GameState getGameState() {
        return GameState;
    }

    public void setGameState(Game.GameState gameState) {
        GameState = gameState;
    }

    public String getGameCode() {
        return GameCode;
    }

    public void setGameCode(String gameCode) {
        GameCode = gameCode;
    }

    public int getGameTime() {
        return GameTime;
    }

    public void setGameTime(int gameTime) {
        GameTime = gameTime;
    }

    public enum GameState{
        Creating,
        Running,
        Ended;
    }

    private HashMap<String,Character> Characters;
    private HashMap<String,HashMap<String, Location>> Locations;
    private GameState GameState;
    private String GameCode;
    private int GameTime;

    public Game(){

    }

}
