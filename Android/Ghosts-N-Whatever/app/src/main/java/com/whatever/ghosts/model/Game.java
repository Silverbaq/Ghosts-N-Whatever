package com.whatever.ghosts.model;

import java.util.HashMap;
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

    public HashMap<String,Character> Characters;
    public HashMap<String,Location> Locations;
    public GameState GameState;
    public String GameCode;
    public int GameTime;

    public Game(){

    }

}
