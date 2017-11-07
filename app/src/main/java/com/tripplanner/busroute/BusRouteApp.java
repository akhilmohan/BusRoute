package com.tripplanner.busroute;

import android.app.Application;
import android.content.Context;

/**
 * Created by akhilmohan on 07/11/17.
 */

public class BusRouteApp extends Application {
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }


    public static Context getContext() {
        return context;
    }
}
