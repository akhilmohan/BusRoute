package com.tripplanner.busroute.ui.routedetails;

import android.os.Bundle;

import com.tripplanner.busroute.data.model.BusRoute;
import com.tripplanner.busroute.data.model.Route;
import com.tripplanner.busroute.ui.base.BaseView;

/**
 * Created by akhilmohan on 07/11/17.
 */

public class RouteDetailContract {
    interface View extends BaseView {
        void showDetails(Route route);
    }

    interface Presenter{
        void onLoad(Bundle extras);
    }
}
