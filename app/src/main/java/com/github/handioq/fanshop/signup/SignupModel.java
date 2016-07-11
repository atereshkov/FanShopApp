package com.github.handioq.fanshop.signup;

import com.github.handioq.fanshop.login.UserAuthState;
import com.github.handioq.fanshop.model.User;

import rx.Observable;

public interface SignupModel {

    Observable<User> getSignupState(User user);

}
