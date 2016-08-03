package com.github.handioq.fanshop.signup;

import com.github.handioq.fanshop.model.dto.UserDTO;
import com.github.handioq.fanshop.net.NetworkService;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;

public class SignupPresenterImpl implements SignupPresenter, SignupModel.Callback {

    private NetworkService networkService;
    private SignupView signupView;
    private SignupModel signupModel;

    @Inject
    public SignupPresenterImpl(NetworkService networkService) {

        signupModel = new SignupModelImpl(networkService);
        signupModel.setCallback(this);
    }

    @Override
    public void signupValidate(UserDTO userDTO) {

        if (signupView != null) {
            signupView.showProgress();
        }

        signupModel.getSignupState(userDTO);
    }

    @Override
    public void onSuccess(UserDTO userDTO) {
        signupView.signupSuccess(userDTO);
        signupView.hideProgress();
    }

    @Override
    public void onError(Throwable error) {
        signupView.signupFailure(error);
        signupView.hideProgress();
    }

    @Override
    public void onCompleted() {
        signupView.onCompleted();
    }

    @Override
    public void setView(SignupView signupView) {
        this.signupView = signupView;
    }

    @Override
    public void onDestroy() {

    }
}
