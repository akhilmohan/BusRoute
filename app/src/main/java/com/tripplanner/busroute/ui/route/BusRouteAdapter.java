package com.tripplanner.busroute.ui.route;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tripplanner.busroute.R;
import com.tripplanner.busroute.data.model.BusRoute;
import com.tripplanner.busroute.data.model.Route;

import java.util.List;

/**
 * Created by akhilmohan on 07/11/17.
 */

public class BusRouteAdapter extends RecyclerView.Adapter<BusRouteAdapter.BusRoutesVH> {

    private List<Route> routes;
    private RoutePresenter.onClickCallback callback;

    public BusRouteAdapter(List<Route> routes,RoutePresenter.onClickCallback callback) {
        this.routes = routes;
        this.callback = callback;
    }

    @Override
    public BusRouteAdapter.BusRoutesVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_bus_routes, parent, false);
        return new BusRoutesVH(view);
    }

    @Override
    public void onBindViewHolder(BusRouteAdapter.BusRoutesVH holder, int position) {
        holder.bind(position, routes.get(position),callback);
    }

    @Override
    public int getItemCount() {
        return routes.size();
    }

    public class BusRoutesVH extends RecyclerView.ViewHolder {
        public TextView txtRouteName,txtRouteDetails;
        public ImageView imgBus;
        public RelativeLayout routeLayout;
        public BusRoutesVH(View view) {
            super(view);
            txtRouteName = (TextView) view.findViewById(R.id.txtRouteName);
            txtRouteDetails = (TextView) view.findViewById(R.id.txtRouteDetails);
            imgBus = (ImageView) view.findViewById(R.id.imgBus);
            routeLayout = (RelativeLayout) view.findViewById(R.id.routeLayout);
        }

        public void bind(int position, Route route, RoutePresenter.onClickCallback callback) {
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
                routeLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        callback.onItemClicked(route);;
                    }
                });
            }
        }
    }
}
