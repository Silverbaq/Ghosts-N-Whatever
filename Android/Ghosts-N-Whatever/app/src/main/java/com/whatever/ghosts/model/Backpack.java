package com.whatever.ghosts.model;

import java.util.List;

/**
 * Created by silverbaq on 29/09/2017.
 */

public class Backpack {

    List<Item> items;

    public Backpack() {

    }

    public List<Item> getItems(){
        return this.items;
    }
    public void setItems(List<Item> items){
        this.items = items;
    }
}
