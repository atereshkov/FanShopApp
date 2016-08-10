package com.github.handioq.fanshop.account;

import android.util.Log;

import com.github.handioq.fanshop.model.dto.UserDTO;
import com.github.handioq.fanshop.net.NetworkService;

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
                .compose(NetworkService.<UserDTO>applyScheduler())
                .subscribe(new Subscriber<UserDTO>() {
                    @Override
                    public void onCompleted() {
                        callback.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onUserLoadError(e);
                    }

                    @Override
                    public void onNext(UserDTO order) {
                        callback.onUserLoaded(order);
                    }
                });

        Log.i(TAG, "getUser()");
    }

    @Override
    public void setCallback(Callback callback) {
        this.callback = callback;
    }
}
