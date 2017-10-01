package com.whatever.ghosts.model;

/**
 * Created by silverbaq on 29/09/2017.
 */

public class Crypt extends Location {

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Ghost getHomeGhost() {
        return homeGhost;
    }

    public void setHomeGhost(Ghost homeGhost) {
        this.homeGhost = homeGhost;
    }

    public String getSetInStateBy() {
        return setInStateBy;
    }

    public void setSetInStateBy(String setInStateBy) {
        this.setInStateBy = setInStateBy;
    }

    public enum State{
        Empty,
        GhostDelivering,
        HunterSettingTrap,
        RobberRobbing,
        GraveTrapped;
    }

    private State state;
    private Ghost homeGhost;
    private String setInStateBy;

    public Crypt(){

    }

}
