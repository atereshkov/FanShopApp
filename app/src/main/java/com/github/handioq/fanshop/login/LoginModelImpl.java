package com.github.handioq.fanshop.login;

import com.github.handioq.fanshop.net.LoginService;
import com.github.handioq.fanshop.net.NetworkService;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginModelImpl implements LoginModel {

    private NetworkService networkService;

    public LoginModelImpl(NetworkService networkService) {
        this.networkService = networkService;
    }

    @Override
    public Observable<UserAuthState> getAuthState(String username, String password) {
        LoginService loginService = networkService.getLoginService();

        /*return loginService.loginRequest(username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());*/
        return (Observable<UserAuthState>)
                networkService.getPreparedObservable(networkService.getLoginService().loginRequest(username, password));
    }
}