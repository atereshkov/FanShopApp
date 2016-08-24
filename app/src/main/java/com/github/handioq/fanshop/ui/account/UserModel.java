package com.github.handioq.fanshop.ui.account;

import android.util.Log;

import com.github.handioq.fanshop.model.dvo.UserDVO;
import com.github.handioq.fanshop.net.NetworkService;
import com.github.handioq.fanshop.util.Mapper;

import rx.Subscriber;

public class UserModel implements UserMvp.Model {

    private final NetworkService networkService;
    private UserMvp.Model.Callback callback;

    private final static String TAG = "UserModel";

    public UserModel(NetworkService networkService) {
        this.networkService = networkService;
    }

    @Override
    public void gerUser(int userId) {
        networkService.getApiService()
                .getUser(userId)
                .map(Mapper::mapUserToDvo)
                .compose(NetworkService.<UserDVO>applyScheduler())
                .subscribe(new Subscriber<UserDVO>() {
                    @Override
                    public void onCompleted() {
                        callback.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onUserLoadError(e);
                    }

                    @Override
                    public void onNext(UserDVO user) {
                        callback.onUserLoaded(user);
                    }
                });

        Log.i(TAG, "getUser()");
    }

    @Override
    public void setCallback(Callback callback) {
        this.callback = callback;
    }
}
