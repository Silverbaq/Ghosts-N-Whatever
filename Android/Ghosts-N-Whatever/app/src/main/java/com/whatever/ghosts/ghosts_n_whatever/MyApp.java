package com.whatever.ghosts.ghosts_n_whatever;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.whatever.ghosts.model.Character;

/**
 * Created by silverbaq on 30/09/2017.
 */

public class MyApp extends Application {
    static FirebaseDatabase database = FirebaseDatabase.getInstance();
    static DatabaseReference myRef = database.getReference("Games");

    public static String gameID = "";
    public static String playerID = "";


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static void AddUpdateCharacter(Character player){
        DatabaseReference db = myRef.child(gameID).child("Players").child(playerID);
        db.setValue(player);
    }
}
