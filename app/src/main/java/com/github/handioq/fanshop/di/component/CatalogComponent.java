package com.github.handioq.fanshop.di.component;

import com.github.handioq.fanshop.ui.catalog.CatalogFragment;
import com.github.handioq.fanshop.ui.catalog.search.SearchFragment;
import com.github.handioq.fanshop.di.module.CatalogModule;
import com.github.handioq.fanshop.di.scope.PresenterScope;

import dagger.Component;

@PresenterScope
@Component(dependencies = NetComponent.class, modules = CatalogModule.class)
public interface CatalogComponent {

    void inject(CatalogFragment catalogFragment);

    void inject(SearchFragment searchFragment);

}