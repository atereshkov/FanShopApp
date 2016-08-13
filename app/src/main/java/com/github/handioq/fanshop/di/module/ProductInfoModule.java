package com.github.handioq.fanshop.di.module;

import com.github.handioq.fanshop.catalog.AddToCartMvp;
import com.github.handioq.fanshop.catalog.AddToCartPresenter;
import com.github.handioq.fanshop.di.scope.PresenterScope;
import com.github.handioq.fanshop.net.NetworkService;
import com.github.handioq.fanshop.productinfo.ProductInfoMvp;
import com.github.handioq.fanshop.productinfo.ProductInfoPresenter;
import com.github.handioq.fanshop.productinfo.ReviewsInfoMvp;
import com.github.handioq.fanshop.productinfo.ReviewsInfoPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class ProductInfoModule {

    @Provides
    @PresenterScope
    public ProductInfoMvp.Presenter providesCatalogPresenter(NetworkService networkService) {
        return new ProductInfoPresenter(networkService);
    }

    @Provides
    @PresenterScope
    public ReviewsInfoMvp.Presenter providesReviewsInfoPresenter(NetworkService networkService) {
        return new ReviewsInfoPresenter(networkService);
    }

    @Provides
    @PresenterScope
    public AddToCartMvp.Presenter providesAddToCartPresenter(NetworkService networkService) {
        return new AddToCartPresenter(networkService);
    }

}