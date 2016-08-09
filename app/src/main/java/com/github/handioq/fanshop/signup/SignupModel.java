package com.github.handioq.fanshop.signup;

import com.github.handioq.fanshop.model.dto.AuthDTO;
import com.github.handioq.fanshop.net.NetworkService;

import rx.Subscriber;

public class SignupModel implements SignupMvp.Model {

    private NetworkService networkService;
    private SignupModel.Callback callback;

    public SignupModel(NetworkService networkService) {
        this.networkService = networkService;
    }

    @Override
    public void getSignupState(AuthDTO authDTO) {

        networkService.getApiService()
                .signup(authDTO)
                .compose(NetworkService.<AuthDTO>applyScheduler())
                .subscribe(new Subscriber<AuthDTO>() {

                    @Override
                    public void onCompleted() {
                        callback.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                       callback.onError(e);
                    }

                    @Override
                    public void onNext(AuthDTO authDTO) { // TODO: check for wrong data or
                       callback.onSuccess(authDTO);
                    }
                });

    }

    @Override
    public void setCallback(Callback callback) {
        this.callback = callback;
    }
}
