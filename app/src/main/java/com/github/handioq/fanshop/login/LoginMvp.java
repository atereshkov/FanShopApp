package com.github.handioq.fanshop.login;

import com.github.handioq.fanshop.base.Mvp;

public interface LoginMvp {

    interface Model extends Mvp.Model {

        void getAuthState(String username, String password);

        void setCallback(Callback callback);

        interface Callback {

            void onSuccess(UserAuthState userAuthState);

            void onError(Throwable error);

            void onCompleted();
        }
    }

    interface View extends Mvp.View {

        void loginSuccess(UserAuthState userAuthState);

        void loginFailure(Throwable e);

        void showProgress();

        void hideProgress(); // badUsername / badPassword?

        void onCompleted();

    }

    interface Presenter extends Mvp.Presenter<LoginMvp.View> {

        void loginValidate(String username, String password);

    }
}
