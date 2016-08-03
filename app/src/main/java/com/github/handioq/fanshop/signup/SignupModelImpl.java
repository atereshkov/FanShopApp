package com.github.handioq.fanshop.signup;

import com.github.handioq.fanshop.model.dto.UserDTO;
import com.github.handioq.fanshop.net.NetworkService;

import rx.Observable;
import rx.Observer;

public class SignupModelImpl implements SignupModel {

    private NetworkService networkService;
    private SignupModel.Callback callback;

    public SignupModelImpl(NetworkService networkService) {
        this.networkService = networkService;
    }

    @Override
    public void getSignupState(UserDTO userDTO) {

        networkService.getApiService()
                .signup(userDTO)
                .compose(NetworkService.<UserDTO>applyScheduler())
                .subscribe(new Observer<UserDTO>() {

                    @Override
                    public void onCompleted() {
                        callback.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                       callback.onError(e);
                    }

                    @Override
                    public void onNext(UserDTO userDTO) { // TODO: check for wrong data or
                       callback.onSuccess(userDTO);
                    }
                });

    }

    @Override
    public void setCallback(Callback callback) {
        this.callback = callback;
    }
}
