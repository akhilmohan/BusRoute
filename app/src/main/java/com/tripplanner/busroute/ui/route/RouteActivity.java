package com.tripplanner.busroute.ui.route;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.tripplanner.busroute.R;
import com.tripplanner.busroute.data.RemoteRepo.RemoteRepository;
import com.tripplanner.busroute.data.RemoteRepo.RouteData;
import com.tripplanner.busroute.data.model.BusRoute;
import com.tripplanner.busroute.data.model.Route;
import com.tripplanner.busroute.ui.routedetails.RoutesDetailActivity;
import com.tripplanner.busroute.utils.Constants;

import io.reactivex.disposables.CompositeDisposable;

public class RouteActivity extends AppCompatActivity implements RouteContract.View{

    private RoutePresenter presenter;
    private RecyclerView rvBusRoutes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        rvBusRoutes = (RecyclerView) findViewById(R.id.recycler_view);
        setSupportActionBar(toolbar);
        presenter = new RoutePresenter(new RouteData(new RemoteRepository(),new CompositeDisposable()));
        presenter.attachView(this);
        presenter.onLoadRoutes(getIntent().getExtras());
    }


    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showDetailScreen(Route route) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.KEY_DATA, route);
        Intent intent = new Intent(this, RoutesDetailActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void showRoutes(BusRoute routes) {
        BusRouteAdapter busRouteAdapter = new BusRouteAdapter(routes.getRoutes(),presenter._onClickCallback);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvBusRoutes.setLayoutManager(layoutManager);
        rvBusRoutes.setHasFixedSize(true);
        rvBusRoutes.setAdapter(busRouteAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView(this);
    }
}
