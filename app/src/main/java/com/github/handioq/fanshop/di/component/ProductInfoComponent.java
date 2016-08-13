package com.github.handioq.fanshop.di.component;


import com.github.handioq.fanshop.di.module.ProductInfoModule;
import com.github.handioq.fanshop.di.scope.PresenterScope;
import com.github.handioq.fanshop.productinfo.ProductInfoFragment;
import com.github.handioq.fanshop.productinfo.ReviewsInfoFragment;

import dagger.Component;

@PresenterScope
@Component(dependencies = NetComponent.class, modules = ProductInfoModule.class)
public interface ProductInfoComponent {

    void inject(ProductInfoFragment productInfoFragment);

    void inject(ReviewsInfoFragment reviewsInfoFragment);

}