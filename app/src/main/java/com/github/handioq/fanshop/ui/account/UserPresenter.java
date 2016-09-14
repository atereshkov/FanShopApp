package com.github.handioq.fanshop.ui.account;

import android.util.Log;

import com.github.handioq.fanshop.model.dvo.UserDVO;
import com.github.handioq.fanshop.net.NetworkService;

import javax.inject.Inject;

public class UserPresenter implements UserMvp.Presenter, UserMvp.Model.Callback{

    private UserMvp.View view;
    private UserMvp.Model model;
    private NetworkService networkService;

    private final static String TAG = "UserPresenter";

    @Inject
    public UserPresenter(NetworkService networkService) {
        model = new UserModel(networkService);
        model.setCallback(this);
    }

    @Override
    public void getUser(int userId) {
        if (view != null) {
            view.showProgress();
            Log.i(TAG, "showLoadProductsProgress() on userView");
        }

        model.gerUser(userId);
    }

    @Override
    public void onUserLoaded(UserDVO user) {
        if (view != null) {
            view.setUser(user);
        }
    }

    @Override
    public void onUserLoadError(Throwable error) {
        if (view != null) {
            view.onError(error);
        }
    }

    @Override
    public void onCompleted() {
        if (view != null) {
            view.hideProgress();
        }
    }

    @Override
    public void setView(UserMvp.View view) {
        this.view = view;
    }
}
