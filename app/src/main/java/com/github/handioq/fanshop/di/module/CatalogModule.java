package com.github.handioq.fanshop.di.module;

import com.github.handioq.fanshop.catalog.AddToCartMvp;
import com.github.handioq.fanshop.catalog.AddToCartPresenter;
import com.github.handioq.fanshop.catalog.CatalogMvp;
import com.github.handioq.fanshop.catalog.CatalogPresenter;
import com.github.handioq.fanshop.catalog.search.SearchMvp;
import com.github.handioq.fanshop.catalog.search.SearchPresenter;
import com.github.handioq.fanshop.di.scope.UserScope;
import com.github.handioq.fanshop.net.NetworkService;

import dagger.Module;
import dagger.Provides;

@Module
public class CatalogModule {

    @Provides
    @UserScope
    public CatalogMvp.Presenter providesCatalogPresenter(NetworkService networkService) {
        return new CatalogPresenter(networkService);
    }

    @Provides
    @UserScope
    public AddToCartMvp.Presenter providesAddToCartPresenter(NetworkService networkService) {
        return new AddToCartPresenter(networkService);
    }

    @Provides
    @UserScope
    public SearchMvp.Presenter providesSearchPresenter(NetworkService networkService) {
        return new SearchPresenter(networkService);
    }
}