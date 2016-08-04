package com.github.handioq.fanshop.signup;

import com.github.handioq.fanshop.model.dto.UserDTO;

import rx.Observable;

public interface SignupModel {

    void getSignupState(UserDTO userDTO);

    void setCallback(Callback callback);

    interface Callback {

        void onSuccess(UserDTO userDTO);

        void onError(Throwable error);

        void onCompleted();
    }

}
