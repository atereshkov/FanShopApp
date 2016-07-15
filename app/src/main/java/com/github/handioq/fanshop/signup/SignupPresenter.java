package com.github.handioq.fanshop.signup;

import com.github.handioq.fanshop.model.User;

public interface SignupPresenter {

    void signupValidate(User user); // what else? whatever...

    void onDestroy();

}
