package com.github.handioq.fanshop.signup;

import com.github.handioq.fanshop.model.dto.UserDTO;

public interface SignupView {

    void signupSuccess(UserDTO userDTO);

    void signupFailure(Throwable e);

    void showProgress();

    void hideProgress();

    void onCompleted();

}
