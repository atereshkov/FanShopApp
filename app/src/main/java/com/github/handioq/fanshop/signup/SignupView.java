package com.github.handioq.fanshop.signup;

import com.github.handioq.fanshop.login.UserAuthState;
import com.github.handioq.fanshop.model.User;

public interface SignupView {

    void signupSuccess(User user);
    void signupFailure(Throwable e);
    void showProgress();
    void hideProgress();

}
