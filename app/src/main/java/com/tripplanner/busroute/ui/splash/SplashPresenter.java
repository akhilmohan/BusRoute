package com.tripplanner.busroute.ui.splash;

import android.os.Bundle;

import com.tripplanner.busroute.ui.base.BasePresenter;

import java.util.concurrent.TimeUnit;

import io.reactivex.Maybe;
import com.tripplanner.busroute.utils.Constants;

/**
 * Created by akhilmohan on 04/11/17.
 */

public class SplashPresenter extends BasePresenter<SplashContract.View> implements SplashContract.Presenter{

    public SplashPresenter(){

    }

    @Override
    public void onLoad(Bundle extras) {
        Maybe.empty()
                .delay(Constants.SPLASH_TIME, TimeUnit.SECONDS)
                .doOnComplete(this::loadHomeScreen)
                .subscribe();

    }

    @Override
    public void loadHomeScreen() {
        getView().showHomeScreen();
    }

}
