package com.github.handioq.fanshop.di.module;

import com.github.handioq.fanshop.categories.CategoriesMvp;
import com.github.handioq.fanshop.categories.CategoriesPresenter;
import com.github.handioq.fanshop.di.scope.UserScope;
import com.github.handioq.fanshop.net.NetworkService;

import dagger.Module;
import dagger.Provides;

@Module
public class CategoriesModule {

    @Provides
    @UserScope
    public CategoriesMvp.Presenter providesCategoriesPresenter(NetworkService networkService) {
        return new CategoriesPresenter(networkService);
    }
}