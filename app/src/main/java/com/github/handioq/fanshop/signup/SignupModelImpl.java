package com.github.handioq.fanshop.signup;

import com.github.handioq.fanshop.model.dto.UserDTO;
import com.github.handioq.fanshop.net.NetworkService;

import rx.Observable;

public class SignupModelImpl implements SignupModel {

    private NetworkService networkService;

    public SignupModelImpl(NetworkService networkService) {
        this.networkService = networkService;
    }

    @Override
    public Observable<UserDTO> getSignupState(UserDTO userDTO) {
        return networkService.getPreparedObservable(networkService.getApiService().signup(userDTO));
    }


}
