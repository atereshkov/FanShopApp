package com.github.handioq.fanshop.signup;

import com.github.handioq.fanshop.base.Mvp;
import com.github.handioq.fanshop.model.dto.AuthDTO;

public interface SignupMvp {

    interface Model extends Mvp.Model {

        void getSignupState(AuthDTO authDTO);

        void setCallback(Callback callback);

        interface Callback {

            void onSuccess(AuthDTO authDTO);

            void onError(Throwable error);

            void onCompleted();
        }
    }

    interface View extends Mvp.View {

        void signupSuccess(AuthDTO authDTO);

        void signupFailure(Throwable e);

        void showProgress();

        void hideProgress();

        void onCompleted();

    }

    interface Presenter extends Mvp.Presenter<SignupMvp.View> {

        void signupValidate(AuthDTO authDTO); // what else? whatever...

    }
}
