package com.github.handioq.fanshop.di.module;

import com.github.handioq.fanshop.cart.interaction.AddToCartMvp;
import com.github.handioq.fanshop.cart.interaction.AddToCartPresenter;
import com.github.handioq.fanshop.di.scope.PresenterScope;
import com.github.handioq.fanshop.net.NetworkService;
import com.github.handioq.fanshop.ui.wishlist.WishlistMvp;
import com.github.handioq.fanshop.ui.wishlist.WishlistPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class WishlistModule {

    @Provides
    @PresenterScope
    public WishlistMvp.Presenter providesWishlistPresenteR(NetworkService networkService) {
        return new WishlistPresenter(networkService);
    }

    @Provides
    @PresenterScope
    public AddToCartMvp.Presenter providesAddToCartPresenter(NetworkService networkService) {
        return new AddToCartPresenter(networkService);
    }
}