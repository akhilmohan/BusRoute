package com.tripplanner.busroute.data.RemoteRepo;

import android.accounts.NetworkErrorException;
import android.content.Context;
import android.util.Log;

import com.tripplanner.busroute.BusRouteApp;
import com.tripplanner.busroute.data.api.BusRouteService;
import com.tripplanner.busroute.data.model.BusRoute;

import io.reactivex.Single;
import io.reactivex.plugins.RxJavaPlugins;
import retrofit2.Call;
import retrofit2.Response;

import static com.tripplanner.busroute.utils.NetworkUtil.isConnected;
import static com.tripplanner.busroute.utils.Constants.BASE_URL;
/**
 * Created by akhilmohan on 07/11/17.
 */

public class RemoteRepository {

    public Single getBusRoute(){
        RxJavaPlugins.setErrorHandler(throwable -> {
            Log.i("Undeliverable", throwable.getMessage());
            return;
        });
        Single<BusRoute> BusRouteSingle = Single.create(singleOnSubscribe -> {
                    if (!isConnected(BusRouteApp.getContext())) {
                        Exception exception = new NetworkErrorException();
                        singleOnSubscribe.onError(exception);
                    } else {
                        try {
                            BusRouteService.BusRouteApi busRouteService= BusRouteService.getInstance().createService(BASE_URL);
                            Call<BusRoute> response = busRouteService.getRoutes();
                            Response<BusRoute> busRouteResponse = response.execute();
                            if(busRouteResponse.isSuccessful()){
                                BusRoute busRoute = (BusRoute)busRouteResponse.body();
                                singleOnSubscribe.onSuccess(busRoute);
                            }else{
                                Throwable throwable = new NetworkErrorException();
                                singleOnSubscribe.onError(throwable);
                            }
                        } catch (Exception e) {
                            singleOnSubscribe.onError(e);
                        }
                    }
        }
        );
        return BusRouteSingle;

        }
}