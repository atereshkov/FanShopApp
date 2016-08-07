package com.github.handioq.fanshop.di.module;

import com.github.handioq.fanshop.cart.CartMvp;
import com.github.handioq.fanshop.cart.CartPresenter;
import com.github.handioq.fanshop.di.scope.UserScope;
import com.github.handioq.fanshop.net.NetworkService;

import dagger.Module;
import dagger.Provides;

@Module
public class CartModule {

    @Provides
    @UserScope
    public CartMvp.Presenter providesCartPresenter(NetworkService networkService) {
        return new CartPresenter(networkService);
    }
}