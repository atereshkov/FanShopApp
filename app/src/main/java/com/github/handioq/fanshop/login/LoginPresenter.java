package com.github.handioq.fanshop.login;

import android.util.Log;

import com.github.handioq.fanshop.net.NetworkService;

import javax.inject.Inject;

public class LoginPresenter implements LoginMvp.Presenter, LoginMvp.Model.Callback {

    private NetworkService networkService;
    private LoginMvp.LoginView loginView;
    private LoginModel loginModel;

    private final static String TAG = "LoginPresenter";

    @Inject
    public LoginPresenter(NetworkService networkService) {

        loginModel = new LoginModel(networkService);
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
    public void setView(LoginMvp.LoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy()");
    }
}
