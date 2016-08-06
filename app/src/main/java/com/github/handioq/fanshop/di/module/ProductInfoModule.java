package com.github.handioq.fanshop.di.module;

import com.github.handioq.fanshop.catalog.AddToCartPresenter;
import com.github.handioq.fanshop.catalog.AddToCartPresenterImpl;
import com.github.handioq.fanshop.di.scope.UserScope;
import com.github.handioq.fanshop.net.NetworkService;
import com.github.handioq.fanshop.productinfo.ProductInfoPresenter;
import com.github.handioq.fanshop.productinfo.ProductInfoPresenterImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class ProductInfoModule {

    @Provides
    @UserScope
    public ProductInfoPresenter providesCatalogPresenter(NetworkService networkService) {
        return new ProductInfoPresenterImpl(networkService);
    }

    @Provides
    @UserScope
    public AddToCartPresenter providesAddToCartPresenter(NetworkService networkService) {
        return new AddToCartPresenterImpl(networkService);
    }
}