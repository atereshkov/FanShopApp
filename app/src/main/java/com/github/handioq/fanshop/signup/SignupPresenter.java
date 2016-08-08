package com.github.handioq.fanshop.signup;

import com.github.handioq.fanshop.model.dto.UserDTO;
import com.github.handioq.fanshop.net.NetworkService;

import javax.inject.Inject;

public class SignupPresenter implements SignupMvp.Presenter, SignupModel.Callback {

    private NetworkService networkService;
    private SignupMvp.View signupView;
    private SignupModel signupModel;

    @Inject
    public SignupPresenter(NetworkService networkService) {

        signupModel = new SignupModel(networkService);
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
    public void setView(SignupMvp.View signupView) {
        this.signupView = signupView;
    }

}
