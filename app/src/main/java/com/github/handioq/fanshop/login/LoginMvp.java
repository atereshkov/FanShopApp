package com.github.handioq.fanshop.login;

public interface LoginMvp {

    interface Model {

        void getAuthState(String username, String password);

        void setCallback(Callback callback);

        interface Callback {

            void onSuccess(UserAuthState userAuthState);

            void onError(Throwable error);

            void onCompleted();
        }
    }

    interface LoginView {

        void loginSuccess(UserAuthState userAuthState);

        void loginFailure(Throwable e);

        void showProgress();

        void hideProgress(); // badUsername / badPassword?

        void onCompleted();

    }

    interface Presenter {

        void loginValidate(String username, String password);

        void onDestroy();

        void setView(LoginView loginView);

    }
}
