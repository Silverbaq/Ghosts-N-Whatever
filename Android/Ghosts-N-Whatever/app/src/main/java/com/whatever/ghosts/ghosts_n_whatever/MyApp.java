package com.whatever.ghosts.ghosts_n_whatever;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * Created by silverbaq on 30/09/2017.
 */

public class MyApp extends Application {
    public static String gameID = "";
    public static String playerID = "";


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
