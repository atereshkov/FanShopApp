package com.github.handioq.fanshop.login;

import com.github.handioq.fanshop.net.NetworkService;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;

public class LoginModel implements LoginMvp.Model {

    private final NetworkService networkService;
    private LoginModel.Callback callback;

    public LoginModel(NetworkService networkService) {
        this.networkService = networkService;
    }

    @Override
    public void getAuthState(String username, String password) {

        networkService.getApiService()
                .login(username, password)
                .compose(NetworkService.<UserAuthState>applyScheduler())
                .subscribe(new Subscriber<UserAuthState>() {
                    @Override
                    public void onCompleted() {
                        callback.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(e);
                    }

                    @Override
                    public void onNext(UserAuthState userAuthState) { // TODO: check for wrong data or
                        callback.onSuccess(userAuthState);
                    }
                });
    }

    @Override
    public void setCallback(Callback callback) {
        this.callback = callback;
    }
}