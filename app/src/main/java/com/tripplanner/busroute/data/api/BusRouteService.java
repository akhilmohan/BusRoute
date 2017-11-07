package com.tripplanner.busroute.data.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.tripplanner.busroute.data.model.BusRoute;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by akhilmohan on 07/11/17.
 */

public class BusRouteService {

    private OkHttpClient.Builder okHttpBuilder;
    private Retrofit retrofit;
    private Gson gson;
    private final int TIMEOUT_CONNECT = 30;
    private final int TIMEOUT_READ = 30;

    public interface BusRouteApi {
        @GET("v2/5808f00d10000005074c6340")
        Call<BusRoute> getRoutes();
    }

    public BusRouteApi createService(String baseUrl) {
        this.okHttpBuilder = new OkHttpClient.Builder();
        okHttpBuilder.connectTimeout(TIMEOUT_CONNECT, TimeUnit.SECONDS);
        okHttpBuilder.readTimeout(TIMEOUT_READ, TimeUnit.SECONDS);
        gson = new GsonBuilder().create();
        OkHttpClient client = okHttpBuilder.build();
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl).client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
        return retrofit.create(BusRouteApi.class);
    }

        private static BusRouteService sBusRouteService;

        private BusRouteService(){}  //private constructor.

        public static BusRouteService getInstance(){
            if (sBusRouteService == null){ //if there is no instance available... create new one
                sBusRouteService = new BusRouteService();
            }

            return sBusRouteService;
        }
}
