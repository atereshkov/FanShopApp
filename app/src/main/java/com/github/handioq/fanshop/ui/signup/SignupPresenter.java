package com.github.handioq.fanshop.ui.signup;

import com.github.handioq.fanshop.net.model.RegisterDTO;
import com.github.handioq.fanshop.net.NetworkService;
import com.github.handioq.fanshop.net.model.Response;

import javax.inject.Inject;

public class SignupPresenter implements SignupMvp.Presenter, SignupModel.Callback {

    private NetworkService networkService;
    private SignupMvp.View view;
    private SignupModel model;

    @Inject
    public SignupPresenter(NetworkService networkService) {

        model = new SignupModel(networkService);
        model.setCallback(this);
    }

    @Override
    public void signupValidate(RegisterDTO registerDTO) {

        if (view != null) {
            view.showProgress();
        }

        model.getSignupState(registerDTO);
    }

    @Override
    public void onSuccess(Response response) {
        if (view != null) {
            view.signupSuccess(response);
            view.hideProgress();
        }
    }

    @Override
    public void onError(Throwable error) {
        if (view != null) {
            view.signupFailure(error);
            view.hideProgress();
        }
    }

    @Override
    public void onCompleted() {
        if (view != null) {
            view.onCompleted();
        }
    }

    @Override
    public void setView(SignupMvp.View signupView) {
        this.view = signupView;
    }

}
