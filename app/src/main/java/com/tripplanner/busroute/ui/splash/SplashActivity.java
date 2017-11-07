package com.tripplanner.busroute.ui.splash;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tripplanner.busroute.R;
import com.tripplanner.busroute.ui.route.RouteActivity;

public class SplashActivity extends AppCompatActivity implements SplashContract.View {

    private SplashPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        presenter = new SplashPresenter();
        presenter.attachView(this);
        presenter.onLoad(getIntent().getExtras());
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showHomeScreen() {
        Intent intent = new Intent(SplashActivity.this, RouteActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView(this);
    }
}
