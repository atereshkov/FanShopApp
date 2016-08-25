package com.github.handioq.fanshop.di.module;

import com.github.handioq.fanshop.ui.categories.CategoriesMvp;
import com.github.handioq.fanshop.ui.categories.CategoriesPresenter;
import com.github.handioq.fanshop.ui.categories.subcategory.SubcategoryMvp;
import com.github.handioq.fanshop.ui.categories.subcategory.SubcategoryPresenter;
import com.github.handioq.fanshop.di.scope.PresenterScope;
import com.github.handioq.fanshop.net.NetworkService;

import dagger.Module;
import dagger.Provides;

@Module
public class CategoriesModule {

    @Provides
    @PresenterScope
    public CategoriesMvp.Presenter providesCategoriesPresenter(NetworkService networkService) {
        return new CategoriesPresenter(networkService);
    }

    @Provides
    @PresenterScope
    public SubcategoryMvp.Presenter providesSubcategoryPresenter(NetworkService networkService) {
        return new SubcategoryPresenter(networkService);
    }
}