package com.github.handioq.fanshop.login;

import rx.Observable;

public interface LoginModel {

    void getAuthState(String username, String password);

    void setCallback(Callback callback);

    interface Callback {

        void onSuccess(UserAuthState userAuthState);

        void onError(Throwable error);

        void onCompleted();
    }

}