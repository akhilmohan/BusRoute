package com.tripplanner.busroute.ui.route;

import android.os.Bundle;

import com.tripplanner.busroute.data.model.BusRoute;
import com.tripplanner.busroute.data.model.Route;
import com.tripplanner.busroute.ui.base.BaseView;

/**
 * Created by akhilmohan on 07/11/17.
 */

public interface RouteContract {

    interface View extends BaseView {
        void showRoutes(BusRoute routes);
        void showDetailScreen(Route route);
    }

    interface Presenter{
        void onLoadRoutes(Bundle extras);
        void onRouteClicked(Route route);
    }
}
