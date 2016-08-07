package com.github.handioq.fanshop.signup;

import com.github.handioq.fanshop.model.dto.UserDTO;

public interface SignupMvp {

    interface Model {

        void getSignupState(UserDTO userDTO);

        void setCallback(Callback callback);

        interface Callback {

            void onSuccess(UserDTO userDTO);

            void onError(Throwable error);

            void onCompleted();
        }
    }

    interface SignupView {

        void signupSuccess(UserDTO userDTO);

        void signupFailure(Throwable e);

        void showProgress();

        void hideProgress();

        void onCompleted();

    }

    interface Presenter {

        void signupValidate(UserDTO userDTO); // what else? whatever...

        void onDestroy();

        void setView(SignupView signupView);

    }

}
