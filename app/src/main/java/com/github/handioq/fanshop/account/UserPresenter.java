package com.github.handioq.fanshop.account;

import android.util.Log;

import com.github.handioq.fanshop.model.dto.UserDTO;
import com.github.handioq.fanshop.net.NetworkService;

import javax.inject.Inject;

public class UserPresenter implements UserMvp.Presenter, UserMvp.Model.Callback{

    private UserMvp.View userView;
    private UserMvp.Model userModel;
    private NetworkService networkService;

    private final static String TAG = "UserPresenter";

    @Inject
    public UserPresenter(NetworkService networkService) {
        userModel = new UserModel(networkService);
        userModel.setCallback(this);
    }

    @Override
    public void getUser(int userId) {
        if (userView != null) {
            userView.showProgress();
            Log.i(TAG, "showLoadProductsProgress() on userView");
        }

        userModel.gerUser(userId);
    }

    @Override
    public void onUserLoaded(UserDTO user) {
        userView.setUser(user);
    }

    @Override
    public void onUserLoadError(Throwable error) {
        userView.onError(error);
    }

    @Override
    public void onCompleted() {
        userView.hideProgress();
    }

    @Override
    public void setView(UserMvp.View view) {
        this.userView = view;
    }
}
