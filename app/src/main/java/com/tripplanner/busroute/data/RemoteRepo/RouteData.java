package com.tripplanner.busroute.data.RemoteRepo;

import com.tripplanner.busroute.data.model.BusRoute;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by akhilmohan on 07/11/17.
 */

public class RouteData {
    public interface RouteDataCallBack{
        public void onSuccess(BusRoute busRoute);
        public void onError();
    }

    private CompositeDisposable compositeDisposable;
    private Disposable routesDisposable;
    Single<BusRoute> BusRouteSingle;
    RemoteRepository repository;
    private DisposableSingleObserver<BusRoute> disposableSingleObserver;

    public RouteData(RemoteRepository repository,CompositeDisposable compositeDisposable) {
        this.compositeDisposable = compositeDisposable;
        this.repository = repository;
    }

    public void getRoutes(RouteDataCallBack callBack){
        disposableSingleObserver = new DisposableSingleObserver<BusRoute>() {
            @Override
            public void onSuccess(BusRoute busRoute) {
                callBack.onSuccess(busRoute);
            }

            @Override
            public void onError(Throwable e) {
                callBack.onError();
            }
        };
        if (!compositeDisposable.isDisposed()) {
            BusRouteSingle = repository.getBusRoute();
            routesDisposable = BusRouteSingle.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribeWith(disposableSingleObserver);
            compositeDisposable.add(routesDisposable);
        }
    }
}
