package com.whatever.ghosts.model;

/**
 * Created by silverbaq on 29/09/2017.
 */

public class Crypt extends Location {

    public enum State{
        Empty,
        GhostDelivering,
        HunterSettingTrap,
        RobberRobbing,
        GraveTrapped;
    }

    public State state;
    public Ghost homeGhost;
    public String setInStateBy;

    public Crypt(){

    }

}
