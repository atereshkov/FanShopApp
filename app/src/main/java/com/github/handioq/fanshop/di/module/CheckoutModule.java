package com.github.handioq.fanshop.di.module;

import com.github.handioq.fanshop.di.scope.PresenterScope;
import com.github.handioq.fanshop.net.NetworkService;
import com.github.handioq.fanshop.ui.checkout.CheckoutMvp;
import com.github.handioq.fanshop.ui.checkout.CheckoutPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class CheckoutModule {

    @Provides
    @PresenterScope
    public CheckoutMvp.Presenter providesCheckoutPresenter(NetworkService networkService) {
        return new CheckoutPresenter(networkService);
    }

}