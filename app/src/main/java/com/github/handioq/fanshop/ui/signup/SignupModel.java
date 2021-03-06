package com.github.handioq.fanshop.ui.signup;

import com.github.handioq.fanshop.net.model.RegisterDTO;
import com.github.handioq.fanshop.net.NetworkService;
import com.github.handioq.fanshop.net.model.Response;

import rx.Subscriber;

public class SignupModel implements SignupMvp.Model {

    private final NetworkService networkService;
    private SignupModel.Callback callback;

    public SignupModel(NetworkService networkService) {
        this.networkService = networkService;
    }

    @Override
    public void getSignupState(RegisterDTO registerDTO) {

        networkService.getApiService()
                .signup(registerDTO)
                .compose(NetworkService.<Response>applyScheduler())
                .subscribe(new Subscriber<Response>() {

                    @Override
                    public void onCompleted() {
                        callback.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                       callback.onError(e);
                    }

                    @Override
                    public void onNext(Response response) { // TODO: check for wrong data or
                       callback.onSuccess(response);
                    }
                });

    }

    @Override
    public void setCallback(Callback callback) {
        this.callback = callback;
    }
}
