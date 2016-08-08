package com.github.handioq.fanshop.signup;

import com.github.handioq.fanshop.base.Mvp;
import com.github.handioq.fanshop.model.dto.UserDTO;

public interface SignupMvp {

    interface Model extends Mvp.Model {

        void getSignupState(UserDTO userDTO);

        void setCallback(Callback callback);

        interface Callback {

            void onSuccess(UserDTO userDTO);

            void onError(Throwable error);

            void onCompleted();
        }
    }

    interface View extends Mvp.View {

        void signupSuccess(UserDTO userDTO);

        void signupFailure(Throwable e);

        void showProgress();

        void hideProgress();

        void onCompleted();

    }

    interface Presenter extends Mvp.Presenter<SignupMvp.View> {

        void signupValidate(UserDTO userDTO); // what else? whatever...

    }
}
