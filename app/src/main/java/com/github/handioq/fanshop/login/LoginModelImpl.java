/*
package com.github.handioq.fanshop.login;

import com.github.handioq.fanshop.net.NetworkService;

import rx.Observable;
import rx.Observer;
import rx.Subscription;

public class LoginModelImpl implements LoginModel {

    private NetworkService networkService;
    private Subscription subscription;

    public LoginModelImpl(NetworkService networkService) {
        this.networkService = networkService;
    }

    @Override
    public void login(final String username, final String password, final OnLoginFinishedListener listener) {
        // Mock login for now.

        Observable<UserAuthState> loginObservable = (Observable<UserAuthState>)
                networkService.getPreparedObservable(networkService.getLoginService().loginRequest(username, password));

        subscription = loginObservable.subscribe(new Observer<UserAuthState>() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                listener.onError(e);
            }

            @Override
            public void onNext(UserAuthState userAuthState) {
                listener.onSuccess(userAuthState);
            }

        });

        */
/*new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                boolean error = false;
                if (TextUtils.isEmpty(username)){
                    listener.onUsernameError();
                    error = true;
                }

                if (TextUtils.isEmpty(password)){
                    listener.onPasswordError();
                    error = true;
                }

                if (!error){
                    listener.onSuccess();
                }
            }
        }, 2000);*//*

    }

}
*/
