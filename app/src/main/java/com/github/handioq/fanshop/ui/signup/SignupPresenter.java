package com.github.handioq.fanshop.ui.signup;

import com.github.handioq.fanshop.net.model.RegisterDTO;
import com.github.handioq.fanshop.net.NetworkService;
import com.github.handioq.fanshop.net.model.Response;

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
    public void signupValidate(RegisterDTO registerDTO) {

        if (signupView != null) {
            signupView.showProgress();
        }

        signupModel.getSignupState(registerDTO);
    }

    @Override
    public void onSuccess(Response response) {
        signupView.signupSuccess(response);
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
