package com.github.handioq.fanshop.di.component;


import com.github.handioq.fanshop.di.module.ProductInfoModule;
import com.github.handioq.fanshop.di.scope.PresenterScope;
import com.github.handioq.fanshop.productinfo.ProductFragment;
import com.github.handioq.fanshop.productinfo.ReviewsFragment;

import dagger.Component;

@PresenterScope
@Component(dependencies = NetComponent.class, modules = ProductInfoModule.class)
public interface ProductInfoComponent {

    void inject(ProductFragment productInfoFragment);

    void inject(ReviewsFragment reviewsInfoFragment);

}