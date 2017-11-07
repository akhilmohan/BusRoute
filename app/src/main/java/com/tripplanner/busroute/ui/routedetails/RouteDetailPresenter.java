package com.tripplanner.busroute.ui.routedetails;

import android.os.Bundle;

import com.tripplanner.busroute.data.model.Route;
import com.tripplanner.busroute.ui.base.BasePresenter;
import com.tripplanner.busroute.utils.Constants;

/**
 * Created by akhilmohan on 07/11/17.
 */

public class RouteDetailPresenter extends BasePresenter<RouteDetailContract.View> implements RouteDetailContract.Presenter{

    @Override
    public void onLoad(Bundle extras) {
        getView().showDetails(extras.getParcelable(Constants.KEY_DATA));
    }
}
