package com.tripplanner.busroute.data.model;

import java.util.List;

/**
 * Created by akhilmohan on 07/11/17.
 */

public class BusRoute {

    private List<Route> routes = null;

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }
}

