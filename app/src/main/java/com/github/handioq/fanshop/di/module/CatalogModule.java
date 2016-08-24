package com.github.handioq.fanshop.di.module;

import com.github.handioq.fanshop.ui.cart.interaction.AddToCartMvp;
import com.github.handioq.fanshop.ui.cart.interaction.AddToCartPresenter;
import com.github.handioq.fanshop.ui.catalog.CatalogMvp;
import com.github.handioq.fanshop.ui.catalog.CatalogPresenter;
import com.github.handioq.fanshop.ui.catalog.search.SearchMvp;
import com.github.handioq.fanshop.ui.catalog.search.SearchPresenter;
import com.github.handioq.fanshop.di.scope.PresenterScope;
import com.github.handioq.fanshop.net.NetworkService;

import dagger.Module;
import dagger.Provides;

@Module
public class CatalogModule {

    @Provides
    @PresenterScope
    public CatalogMvp.Presenter providesCatalogPresenter(NetworkService networkService) {
        return new CatalogPresenter(networkService);
    }

    @Provides
    @PresenterScope
    public SearchMvp.Presenter providesSearchPresenter(NetworkService networkService) {
        return new SearchPresenter(networkService);
    }

    @Provides
    @PresenterScope
    public AddToCartMvp.Presenter providesAddToCartPresenter(NetworkService networkService) {
        return new AddToCartPresenter(networkService);
    }
}