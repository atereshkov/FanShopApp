package com.github.handioq.fanshop.login;

public interface LoginView {

    void loginSuccess(UserAuthState userAuthState);
    void loginFailure(Throwable e);
    void showProgress();
    void hideProgress();
    // badUsername / badPassword?

}
