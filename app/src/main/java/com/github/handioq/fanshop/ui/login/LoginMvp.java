package com.github.handioq.fanshop.ui.login;

import com.github.handioq.fanshop.base.Mvp;
import com.github.handioq.fanshop.net.model.AuthResponse;

public interface LoginMvp {

    interface Model extends Mvp.Model {

        void getAuthState(String mail, String password);

        void setCallback(Callback callback);

        interface Callback {

            void onSuccess(AuthResponse authResponse);

            void onError(Throwable error);

            void onCompleted();
        }
    }

    interface View extends Mvp.View {

        void loginSuccess(AuthResponse authResponse);

        void loginFailure(Throwable e);

        void showProgress();

        void hideProgress(); // badUsername / badPassword?

        void onCompleted();

    }

    interface Presenter extends Mvp.Presenter<LoginMvp.View> {

        void loginValidate(String mail, String password);

    }
}
