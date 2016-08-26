package com.github.handioq.fanshop.ui.login;

import com.github.handioq.fanshop.net.NetworkService;
import com.github.handioq.fanshop.net.model.LoginDTO;
import com.github.handioq.fanshop.net.model.Token;

import javax.inject.Inject;

public class LoginPresenter implements LoginMvp.Presenter, LoginMvp.Model.Callback {

    private NetworkService networkService;
    private LoginMvp.View loginView;
    private LoginModel loginModel;

    private final static String TAG = "LoginPresenter";

    @Inject
    public LoginPresenter(NetworkService networkService) {

        loginModel = new LoginModel(networkService);
        loginModel.setCallback(this);
    }

    @Override
    public void loginValidate(LoginDTO loginDTO) {

        if (loginView != null) {
            loginView.showProgress();
        }

        loginModel.getAuthState(loginDTO);
    }

    @Override
    public void onSuccess(Token authResponse) {
        loginView.loginSuccess(authResponse);
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
    public void setView(LoginMvp.View loginView) {
        this.loginView = loginView;
    }

}
