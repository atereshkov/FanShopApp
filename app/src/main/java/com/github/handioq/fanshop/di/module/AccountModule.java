package com.github.handioq.fanshop.di.module;

import com.github.handioq.fanshop.account.OrderMvp;
import com.github.handioq.fanshop.account.OrderPresenter;
import com.github.handioq.fanshop.account.UserMvp;
import com.github.handioq.fanshop.account.UserPresenter;
import com.github.handioq.fanshop.di.scope.UserScope;
import com.github.handioq.fanshop.net.NetworkService;

import dagger.Module;
import dagger.Provides;

@Module
public class AccountModule {

    @Provides
    @UserScope
    public OrderMvp.Presenter providesOrderPresenter(NetworkService networkService) {
        return new OrderPresenter(networkService);
    }

    @Provides
    @UserScope
    public UserMvp.Presenter providesUserPresenter(NetworkService networkService) {
        return new UserPresenter(networkService);
    }
}