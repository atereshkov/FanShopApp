package com.github.handioq.fanshop.login;

import android.util.Log;

import com.github.handioq.fanshop.net.NetworkService;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import rx.Subscription;

public class LoginPresenterImpl implements LoginPresenter, LoginModel.Callback {

    private NetworkService networkService;
    private LoginView loginView;
    private LoginModel loginModel;

    private final static String TAG = "LoginPresenterImpl";

    @Inject
    public LoginPresenterImpl(NetworkService networkService) {

        loginModel = new LoginModelImpl(networkService);
        loginModel.setCallback(this);
    }

    @Override
    public void loginValidate(String username, String password) {

        if (loginView != null) {
            loginView.showProgress();
        }

        loginModel.getAuthState(username, password);
    }

    @Override
    public void onSuccess(UserAuthState userAuthState) {
        loginView.loginSuccess(userAuthState);
        loginView.hideProgress();
    }

    @Override
    public void onError(Throwable error) {
        loginView.loginFailure(error);
        loginView.hideProgress();
    }

    @Override
    public void onCompleted() {
        loginView.onCompleted();
    }

    @Override
    public void setView(LoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy()");
    }
}
