package com.github.handioq.fanshop.ui.login;

import com.github.handioq.fanshop.net.NetworkService;
import com.github.handioq.fanshop.net.model.LoginDTO;
import com.github.handioq.fanshop.net.model.Token;

import rx.Subscriber;

public class LoginModel implements LoginMvp.Model {

    private final NetworkService networkService;
    private LoginModel.Callback callback;

    public LoginModel(NetworkService networkService) {
        this.networkService = networkService;
    }

    @Override
    public void getAuthState(LoginDTO loginDTO) {

        networkService.getApiService()
                .login(loginDTO)
                .compose(NetworkService.<Token>applyScheduler())
                .subscribe(new Subscriber<Token>() {
                    @Override
                    public void onCompleted() {
                        callback.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(e);
                    }

                    @Override
                    public void onNext(Token authResponse) {
                        callback.onSuccess(authResponse);
                    }
                });
    }

    @Override
    public void setCallback(Callback callback) {
        this.callback = callback;
    }
}