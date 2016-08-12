package com.github.handioq.fanshop.di.module;

import com.github.handioq.fanshop.account.OrderMvp;
import com.github.handioq.fanshop.account.OrderPresenter;
import com.github.handioq.fanshop.account.UserMvp;
import com.github.handioq.fanshop.account.UserPresenter;
import com.github.handioq.fanshop.di.scope.PresenterScope;
import com.github.handioq.fanshop.net.NetworkService;

import dagger.Module;
import dagger.Provides;

@Module
public class AccountModule {

    @Provides
    @PresenterScope
    public OrderMvp.Presenter providesOrderPresenter(NetworkService networkService) {
        return new OrderPresenter(networkService);
    }

    @Provides
    @PresenterScope
    public UserMvp.Presenter providesUserPresenter(NetworkService networkService) {
        return new UserPresenter(networkService);
    }
}