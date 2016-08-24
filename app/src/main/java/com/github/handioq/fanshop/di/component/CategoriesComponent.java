package com.github.handioq.fanshop.di.component;

import com.github.handioq.fanshop.ui.categories.CategoriesFragment;
import com.github.handioq.fanshop.ui.categories.subcategory.SubcategoryFragment;
import com.github.handioq.fanshop.di.module.CategoriesModule;
import com.github.handioq.fanshop.di.scope.PresenterScope;

import dagger.Component;

@PresenterScope
@Component(dependencies = NetComponent.class, modules = CategoriesModule.class)
public interface CategoriesComponent {

    void inject(CategoriesFragment categoriesFragment);

    void inject(SubcategoryFragment subcategoryFragment);

}