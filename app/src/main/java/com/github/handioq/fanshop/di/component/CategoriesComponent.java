package com.github.handioq.fanshop.di.component;

import com.github.handioq.fanshop.categories.CategoriesFragment;
import com.github.handioq.fanshop.di.module.CategoriesModule;
import com.github.handioq.fanshop.di.scope.UserScope;

import dagger.Component;

@UserScope
@Component(dependencies = NetComponent.class, modules = CategoriesModule.class)
public interface CategoriesComponent {

    void inject(CategoriesFragment categoriesFragment);

}