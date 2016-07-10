package com.github.handioq.fanshop.login;

public interface LoginPresenter {

    void loginValidate(String username, String password);
    void onDestroy();

}
