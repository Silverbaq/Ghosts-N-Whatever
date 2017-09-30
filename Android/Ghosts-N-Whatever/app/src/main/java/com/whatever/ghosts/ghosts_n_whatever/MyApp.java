package com.whatever.ghosts.ghosts_n_whatever;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.whatever.ghosts.model.Character;
import com.whatever.ghosts.model.Location;

/**
 * Created by silverbaq on 30/09/2017.
 */

public class MyApp extends Application {
    static FirebaseDatabase database;
    static DatabaseReference myRef;

    public static String gameID = "";
    public static String playerID = "";

    @Override
    public void onCreate() {
        super.onCreate();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Games");
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static void AddUpdateCharacter(Character player) {
        DatabaseReference db = myRef.child(gameID).child("Players").child(playerID);
        db.setValue(player);
    }

    public static void AddUpdateLocation(Location location) {
        DatabaseReference db = null;
        if (location.getVillage())
            db = myRef.child(gameID).child("Locations").child("Village").child(location.Key);
        else
            db = myRef.child(gameID).child("Locations").child("Crypt").child(location.Key);

        db.setValue(location);
    }
}
