package com.whatever.ghosts.model;

import com.whatever.ghosts.ghosts_n_whatever.MyApp;

/**
 * Created by silverbaq on 29/09/2017.
 */

public class Location {

    public String Name;

    public Location() {

    }

    public void Arriving(Character player) {
        if (this.getClass().equals(Village.class)) {
            switch (player.CharacterClass) {
                case Ghost:
                    Item item1 = new Item();
                    item1.Name = "Soul";
                    item1.Value = 10;
                    player.Backpack.addItem(item1);
                    break;
                case Robber:
                    int value = player.Backpack.UseItem();
                    player.Score += value;
                    break;
                case Hunter:
                    Item item3 = new Item();
                    item3.Name = "Trap";
                    item3.Value = 10;
                    player.Score -= 10;
                    player.Backpack.addItem(item3);
                    break;
            }
        } else if (this.getClass().equals(Crypt.class)){
            Crypt crypt = (Crypt)this;
            switch (player.CharacterClass){
                case Ghost:
                    if (player.Home.equals(this.Name)){
                        if (crypt.state == Crypt.State.Empty) {
                            int value = player.Backpack.UseItem();
                            player.Score += value;
                        }
                    }
                    break;
                case Hunter:
                    break;
                case Robber:
                    break;
            }
        }
        MyApp.AddUpdateCharacter(player);
    }

}
