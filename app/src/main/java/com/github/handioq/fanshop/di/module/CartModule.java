package com.github.handioq.fanshop.di.module;

import com.github.handioq.fanshop.cart.CartMvp;
import com.github.handioq.fanshop.cart.CartPresenter;
import com.github.handioq.fanshop.cart.RemoveFromCartMvp;
import com.github.handioq.fanshop.cart.RemoveFromCartPresenter;
import com.github.handioq.fanshop.di.scope.PresenterScope;
import com.github.handioq.fanshop.net.NetworkService;

import dagger.Module;
import dagger.Provides;

@Module
public class CartModule {

    @Provides
    @PresenterScope
    public CartMvp.Presenter providesCartPresenter(NetworkService networkService) {
        return new CartPresenter(networkService);
    }

    @Provides
    @PresenterScope
    public RemoveFromCartMvp.Presenter providesRemoveFromCartPresenter(NetworkService networkService) {
        return new RemoveFromCartPresenter(networkService);
    }
}