package com.github.handioq.fanshop.login;

import com.github.handioq.fanshop.net.NetworkService;

import rx.Observable;

public class LoginModelImpl implements LoginModel {

    private NetworkService networkService;

    public LoginModelImpl(NetworkService networkService) {
        this.networkService = networkService;
    }

    @Override
    public Observable<UserAuthState> getAuthState(String username, String password) {
        return (Observable<UserAuthState>)
                networkService.getPreparedObservable(networkService.getLoginService().loginRequest(username, password));
    }
}