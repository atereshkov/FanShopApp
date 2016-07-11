package com.github.handioq.fanshop.signup;

import com.github.handioq.fanshop.login.UserAuthState;
import com.github.handioq.fanshop.model.User;
import com.github.handioq.fanshop.net.NetworkService;

import rx.Observable;

public class SignupModelImpl implements SignupModel {

    private NetworkService networkService;

    public SignupModelImpl(NetworkService networkService) {
        this.networkService = networkService;
    }

    @Override
    public Observable<User> getSignupState(User user) {
        return (Observable<User>)
                networkService.getPreparedObservable(networkService.getLoginService().signupRequest(user));
    }


}
