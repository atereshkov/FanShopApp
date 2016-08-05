package com.github.handioq.fanshop.di.module;

import com.github.handioq.fanshop.cart.CartPresenter;
import com.github.handioq.fanshop.cart.CartPresenterImpl;
import com.github.handioq.fanshop.di.scope.UserScope;
import com.github.handioq.fanshop.net.NetworkService;

import dagger.Module;
import dagger.Provides;

@Module
public class CartModule {

    @Provides
    @UserScope
    public CartPresenter providesCartPresenter(NetworkService networkService) {
        return new CartPresenterImpl(networkService);
    }
}