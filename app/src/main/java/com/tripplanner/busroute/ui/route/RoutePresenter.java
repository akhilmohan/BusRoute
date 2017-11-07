package com.tripplanner.busroute.ui.route;

import android.os.Bundle;
import android.util.Log;

import com.tripplanner.busroute.data.RemoteRepo.RouteData;
import com.tripplanner.busroute.data.model.BusRoute;
import com.tripplanner.busroute.data.model.Route;
import com.tripplanner.busroute.ui.base.BasePresenter;

/**
 * Created by akhilmohan on 07/11/17.
 */

public class RoutePresenter extends BasePresenter<RouteContract.View> implements RouteContract.Presenter{

    RouteData routeData;

    public RoutePresenter(RouteData routeData){
        this.routeData = routeData;
    }

    @Override
    public void onLoadRoutes(Bundle extras) {
        getView().showProgress();
        routeData.getRoutes(callBack);
    }

    @Override
    public void onRouteClicked(Route route) {
        getView().showDetailScreen(route);
    }

    RouteData.RouteDataCallBack callBack = new RouteData.RouteDataCallBack() {
        @Override
        public void onSuccess(BusRoute busRoute) {
            Log.d("Routes","Routes loaded");
            getView().showRoutes(busRoute);
            getView().hideProgress();
        }

        @Override
        public void onError() {
            Log.d("Routes","Routes error");
            getView().hideProgress();
        }
    };

    public interface onClickCallback{
        public void onItemClicked(Route route);
    }

    onClickCallback _onClickCallback = new onClickCallback() {
        @Override
        public void onItemClicked(Route route) {
            onRouteClicked(route);
        }
    };
}
