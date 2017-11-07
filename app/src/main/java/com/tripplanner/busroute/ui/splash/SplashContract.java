package com.tripplanner.busroute.ui.splash;

import android.os.Bundle;

import com.tripplanner.busroute.ui.base.BaseView;

/**
 * Created by akhilmohan on 04/11/17.
 */

public interface SplashContract {

    interface View extends BaseView{
        void showHomeScreen();
    }

    interface Presenter{
        void onLoad(Bundle extras);
        void loadHomeScreen();
    }
}
