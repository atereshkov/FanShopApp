package com.github.handioq.fanshop.login;

import rx.Observable;

public interface LoginModel {

    Observable<UserAuthState> getAuthState(String username, String password);

}