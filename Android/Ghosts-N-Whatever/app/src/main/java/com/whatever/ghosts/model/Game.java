package com.whatever.ghosts.model;

import java.util.List;

/**
 * Created by silverbaq on 29/09/2017.
 */

public class Game {

    public enum GameState{
        Creating,
        Running,
        Ended;
    }

    public List<Character> Characters;
    public List<Location> Locations;
    public GameState GameState;
    public String GameCode;
    public int GameTime;

    public Game(){

    }

}
