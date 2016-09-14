package com.github.handioq.fanshop.di.module;

import com.github.handioq.fanshop.ui.account.OrderMvp;
import com.github.handioq.fanshop.ui.account.OrderPresenter;
import com.github.handioq.fanshop.ui.account.UserMvp;
import com.github.handioq.fanshop.ui.account.UserPresenter;
import com.github.handioq.fanshop.di.scope.PresenterScope;
import com.github.handioq.fanshop.net.NetworkService;
import com.github.handioq.fanshop.ui.account.orders.OrderDetailsMvp;
import com.github.handioq.fanshop.ui.account.orders.OrderDetailsPresenter;
import com.github.handioq.fanshop.ui.account.orders.iteraction.PayMvp;
import com.github.handioq.fanshop.ui.account.orders.iteraction.PayPresenter;

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

    @Provides
    @PresenterScope
    public OrderDetailsMvp.Presenter providesOrderDetailsPresenter(NetworkService networkService) {
        return new OrderDetailsPresenter(networkService);
    }

    @Provides
    @PresenterScope
    public PayMvp.Presenter providesPayPresenter(NetworkService networkService) {
        return new PayPresenter(networkService);
    }
}