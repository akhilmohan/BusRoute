package com.tripplanner.busroute.ui.routedetails;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tripplanner.busroute.R;
import com.tripplanner.busroute.data.model.Route;
import com.tripplanner.busroute.data.model.Stop;
import com.tripplanner.busroute.ui.splash.SplashPresenter;
import com.tripplanner.busroute.utils.Stepper;

public class RoutesDetailActivity extends AppCompatActivity implements RouteDetailContract.View{

    private RouteDetailPresenter presenter;
    private TextView txtRouteName,txtRouteDetails;
    private ImageView imgBus,imgAccessibile;
    private Stepper stepper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routes_detail);
        txtRouteName = (TextView) findViewById(R.id.txtRouteName);
        txtRouteDetails = (TextView) findViewById(R.id.txtRouteDetails);
        imgBus = (ImageView) findViewById(R.id.imgBus);
        imgAccessibile = (ImageView) findViewById(R.id.imgAccessible);
        stepper = (Stepper) findViewById(R.id.step1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        presenter = new RouteDetailPresenter();
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
    public void showDetails(Route route) {
        if(route != null)
        {
            if(!TextUtils.isEmpty(route.getName())){
                txtRouteName.setText(route.getName());
            }
            if(!TextUtils.isEmpty(route.getDescription())){
                txtRouteDetails.setText(route.getDescription());
            }
            if(!TextUtils.isEmpty(route.getImage())){
                Picasso.with(imgBus.getContext()).load(route.getImage()).placeholder(R.drawable.placeholder).into(imgBus);
            }
            imgAccessibile.setVisibility(route.getAccessible().equalsIgnoreCase("true")?View.VISIBLE:View.INVISIBLE);
        }
        txtRouteName.setText(route.getName());
        String[] stops = new String[route.getStops().size()];
        int i = 0;
        for(Stop _stops:route.getStops()){
            stops[i] = _stops.getName();
            i++;
        }
        stepper.setStops(stops);
    }
}
