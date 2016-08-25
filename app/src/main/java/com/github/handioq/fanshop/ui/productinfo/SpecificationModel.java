package com.github.handioq.fanshop.ui.productinfo;

import android.util.Log;

import com.github.handioq.fanshop.model.dvo.SpecificationDVO;
import com.github.handioq.fanshop.net.NetworkService;
import com.github.handioq.fanshop.util.Mapper;

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
                .map(Mapper::mapSpecificationToDvo)
                .compose(NetworkService.<SpecificationDVO>applyScheduler())
                .subscribe(new Subscriber<SpecificationDVO>() {
                    @Override
                    public void onCompleted() {
                        callback.onSpecificationLoadCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onSpecificationLoadError(e);
                    }

                    @Override
                    public void onNext(SpecificationDVO specification) {
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