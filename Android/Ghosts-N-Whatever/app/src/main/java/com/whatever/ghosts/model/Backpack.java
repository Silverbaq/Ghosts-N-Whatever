package com.whatever.ghosts.model;

import java.util.List;

/**
 * Created by silverbaq on 29/09/2017.
 */

public class Backpack {

    public List<Item> Items;

    public Backpack() {

    }

    public List<Item> getItems() {
        return this.Items;
    }

    public void addItem(Item item) {
        if (this.Items.size() == 0) {
            Items.add(item);
        }
    }

    public int UseItem() {
        if (this.Items.size() > 0) {
            Item item = Items.remove(0);
            return item.Value;
        }
        return 0;
    }
}
