package com.github.handioq.fanshop.di.component;

import com.github.handioq.fanshop.catalog.CatalogFragment;
import com.github.handioq.fanshop.di.module.CatalogModule;
import com.github.handioq.fanshop.di.scope.UserScope;

import dagger.Component;

@UserScope
@Component(dependencies = NetComponent.class, modules = CatalogModule.class)
public interface CatalogComponent {

    void inject(CatalogFragment catalogFragment);

}