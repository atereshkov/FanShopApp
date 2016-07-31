package com.github.handioq.fanshop.signup;

import com.github.handioq.fanshop.model.dto.UserDTO;

import rx.Observable;

public interface SignupModel {

    Observable<UserDTO> getSignupState(UserDTO userDTO);

}
