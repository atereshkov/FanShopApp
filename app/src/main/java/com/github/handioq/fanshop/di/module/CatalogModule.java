package com.github.handioq.fanshop.di.module;

import android.view.View;

import com.github.handioq.fanshop.catalog.CatalogPresenter;
import com.github.handioq.fanshop.catalog.CatalogPresenterImpl;
import com.github.handioq.fanshop.catalog.CatalogView;
import com.github.handioq.fanshop.di.scope.UserScope;
import com.github.handioq.fanshop.model.dto.ProductDTO;
import com.github.handioq.fanshop.net.NetworkService;

import java.util.List;

import dagger.Module;
import dagger.Provides;

@Module
public class CatalogModule {

    @Provides
    @UserScope
    public CatalogPresenterImpl providesCatalogPresenter(NetworkService networkService) {
        return new CatalogPresenterImpl(networkService);
    }
}