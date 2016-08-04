package com.github.handioq.fanshop.di.component;


import com.github.handioq.fanshop.di.module.ProductInfoModule;
import com.github.handioq.fanshop.di.scope.UserScope;
import com.github.handioq.fanshop.productinfo.ProductInfoFragment;

import dagger.Component;

@UserScope
@Component(dependencies = NetComponent.class, modules = ProductInfoModule.class)
public interface ProductInfoComponent {

    void inject(ProductInfoFragment productInfoFragment);

}