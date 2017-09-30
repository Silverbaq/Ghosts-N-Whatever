package com.whatever.ghosts.model;

import java.util.List;

/**
 * Created by silverbaq on 29/09/2017.
 */

public class Backpack {

    private List<Item> Items;

    public Backpack() {

    }

    public List<Item> getItems() {
        return this.Items;
    }

    public void addItem(Item item) {
        if (this.getItems().size() == 0) {
            getItems().add(item);
        }
    }

    public int UseItem() {
        if (this.getItems().size() > 0) {
            Item item = getItems().remove(0);
            return item.getValue();
        }
        return 0;
    }


    public void setItems(List<Item> items) {
        Items = items;
    }
}
