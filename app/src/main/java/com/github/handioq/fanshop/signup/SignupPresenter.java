package com.github.handioq.fanshop.signup;

import com.github.handioq.fanshop.model.UserDTO;

public interface SignupPresenter {

    void signupValidate(UserDTO userDTO); // what else? whatever...

    void onDestroy();

}
