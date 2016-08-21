package com.github.handioq.fanshop.productinfo;

import android.util.Log;

import com.github.handioq.fanshop.model.dto.SpecificationDTO;
import com.github.handioq.fanshop.net.NetworkService;

import rx.Subscriber;

public class SpecificationModel implements SpecificationMvp.Model {

    private final NetworkService networkService;
    private SpecificationModel.Callback callback;

    private final static String TAG = "SpecificationModel";

    public SpecificationModel(NetworkService networkService) {
        this.networkService = networkService;
    }

    @Override
    public void getSpecification(int id) {
        networkService.getApiService()
                .getSpecification(id)
                .compose(NetworkService.<SpecificationDTO>applyScheduler())
                .subscribe(new Subscriber<SpecificationDTO>() {
                    @Override
                    public void onCompleted() {
                        callback.onSpecificationLoadCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onSpecificationLoadError(e);
                    }

                    @Override
                    public void onNext(SpecificationDTO specification) {
                        callback.onSpecificationLoaded(specification);
                    }
                });

        Log.i(TAG, "getSpecification()");
    }

    @Override
    public void setCallback(Callback callback) {
        this.callback = callback;
    }
}