package com.github.handioq.fanshop.di.module;

import com.github.handioq.fanshop.catalog.AddToCartMvp;
import com.github.handioq.fanshop.catalog.AddToCartPresenter;
import com.github.handioq.fanshop.di.scope.PresenterScope;
import com.github.handioq.fanshop.net.NetworkService;
import com.github.handioq.fanshop.productinfo.ProductMvp;
import com.github.handioq.fanshop.productinfo.ProductPresenter;
import com.github.handioq.fanshop.productinfo.ReviewsMvp;
import com.github.handioq.fanshop.productinfo.ReviewsPresenter;
import com.github.handioq.fanshop.ui.wishlist.interaction.AddToWishlistMvp;
import com.github.handioq.fanshop.ui.wishlist.interaction.AddToWishlistPresenter;
import com.github.handioq.fanshop.ui.wishlist.interaction.RemoveWishlistMvp;
import com.github.handioq.fanshop.ui.wishlist.interaction.RemoveWishlistPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class ProductInfoModule {

    @Provides
    @PresenterScope
    public ProductMvp.Presenter providesCatalogPresenter(NetworkService networkService) {
        return new ProductPresenter(networkService);
    }

    @Provides
    @PresenterScope
    public ReviewsMvp.Presenter providesReviewsInfoPresenter(NetworkService networkService) {
        return new ReviewsPresenter(networkService);
    }

    @Provides
    @PresenterScope
    public AddToCartMvp.Presenter providesAddToCartPresenter(NetworkService networkService) {
        return new AddToCartPresenter(networkService);
    }

    @Provides
    @PresenterScope
    public AddToWishlistMvp.Presenter providesAddToWishlistPresenter(NetworkService networkService) {
        return new AddToWishlistPresenter(networkService);
    }

    @Provides
    @PresenterScope
    public RemoveWishlistMvp.Presenter providesRemoveWishlistPresenter(NetworkService networkService) {
        return new RemoveWishlistPresenter(networkService);
    }
}