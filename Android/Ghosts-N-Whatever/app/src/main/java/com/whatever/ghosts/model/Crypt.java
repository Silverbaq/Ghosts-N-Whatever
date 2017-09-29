package com.whatever.ghosts.model;

/**
 * Created by silverbaq on 29/09/2017.
 */

public class Crypt extends Location {

    public enum State{
        EMPTY,
        GHOSTDELIVERING,
        HUNTERSETTINGTRAP,
        ROBBERROBBING,
        GRAVETRAPPED;
    }

    State state;
    Ghost homeGhost;

    public Crypt(){

    }


    public State getState(){
        return this.state;
    }

    public void setState(State state){
        this.state = state;
    }

    public Ghost getHomeGhost(){
        return this.homeGhost;
    }

    public void setHomeGhost(Ghost ghost){
        this.homeGhost = ghost;
    }
}
