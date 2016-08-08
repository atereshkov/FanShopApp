package com.github.handioq.fanshop.account;

import android.util.Log;

import com.github.handioq.fanshop.model.dto.OrderDTO;
import com.github.handioq.fanshop.net.NetworkService;

import java.util.List;

import javax.inject.Inject;

public class AccountPresenter implements AccountMvp.Presenter, AccountMvp.Model.Callback {

    private AccountMvp.View accountView;
    private AccountMvp.Model accountModel;
    private NetworkService networkService;

    private final static String TAG = "AccountPresenter";

    @Inject
    public AccountPresenter(NetworkService networkService) {
        accountModel = new AccountModel(networkService);
        accountModel.setCallback(this);
    }

    @Override
    public void getOrders(int userId) {
        if (accountView != null) {
            accountView.showProgress();
            Log.i(TAG, "showProgress() on accountView");
        }

        accountModel.gerOrders(userId);
    }

    @Override
    public void setView(AccountMvp.View view) {
        this.accountView = view;
    }

    @Override
    public void onOrdersLoaded(List<OrderDTO> orders) {
        accountView.setOrders(orders);
    }

    @Override
    public void onOrdersLoadError(Throwable error) {
        accountView.onError(error);
    }

    @Override
    public void onCompleted() {
        accountView.hideProgress();
    }
}
