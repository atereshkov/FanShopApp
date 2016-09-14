package com.github.handioq.fanshop.ui.login;

import com.github.handioq.fanshop.net.model.AuthResponse;
import com.github.handioq.fanshop.net.NetworkService;

import javax.inject.Inject;

public class LoginPresenter implements LoginMvp.Presenter, LoginMvp.Model.Callback {

    private NetworkService networkService;
    private LoginMvp.View view;
    private LoginModel model;

    private final static String TAG = "LoginPresenter";

    @Inject
    public LoginPresenter(NetworkService networkService) {

        model = new LoginModel(networkService);
        model.setCallback(this);
    }

    @Override
    public void loginValidate(String mail, String password) {

        if (view != null) {
            view.showProgress();
        }

        model.getAuthState(mail, password);
    }

    @Override
    public void onSuccess(AuthResponse authResponse) {
        if (view != null) {
            view.loginSuccess(authResponse);
            view.hideProgress();
        }
    }

    @Override
    public void onError(Throwable error) {
        if (view != null) {
            view.loginFailure(error);
            view.hideProgress();
        }
    }

    @Override
    public void onCompleted() {
        if (view != null) {
            view.onCompleted();
        }
    }

    @Override
    public void setView(LoginMvp.View loginView) {
        this.view = loginView;
    }

}
