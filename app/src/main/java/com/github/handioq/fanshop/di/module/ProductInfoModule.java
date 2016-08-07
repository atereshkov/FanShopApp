package com.github.handioq.fanshop.di.module;

import com.github.handioq.fanshop.catalog.AddToCartMvp;
import com.github.handioq.fanshop.catalog.AddToCartPresenter;
import com.github.handioq.fanshop.di.scope.UserScope;
import com.github.handioq.fanshop.net.NetworkService;
import com.github.handioq.fanshop.productinfo.ProductInfoMvp;
import com.github.handioq.fanshop.productinfo.ProductInfoPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class ProductInfoModule {

    @Provides
    @UserScope
    public ProductInfoMvp.Presenter providesCatalogPresenter(NetworkService networkService) {
        return new ProductInfoPresenter(networkService);
    }

    @Provides
    @UserScope
    public AddToCartMvp.Presenter providesAddToCartPresenter(NetworkService networkService) {
        return new AddToCartPresenter(networkService);
    }
}