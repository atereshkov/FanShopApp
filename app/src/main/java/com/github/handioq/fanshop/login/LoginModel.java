package com.github.handioq.fanshop.login;

import com.github.handioq.fanshop.net.model.AuthResponse;
import com.github.handioq.fanshop.net.NetworkService;
import com.github.handioq.fanshop.net.model.LoginDTO;

import rx.Subscriber;

public class LoginModel implements LoginMvp.Model {

    private final NetworkService networkService;
    private LoginModel.Callback callback;

    public LoginModel(NetworkService networkService) {
        this.networkService = networkService;
    }

    @Override
    public void getAuthState(String mail, String password) {

        networkService.getApiService()
                .login(mail, password)
                .compose(NetworkService.<AuthResponse>applyScheduler())
                .subscribe(new Subscriber<AuthResponse>() {
                    @Override
                    public void onCompleted() {
                        callback.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(e);
                    }

                    @Override
                    public void onNext(AuthResponse authResponse) {
                        callback.onSuccess(authResponse);
                    }
                });
    }

    @Override
    public void setCallback(Callback callback) {
        this.callback = callback;
    }
}