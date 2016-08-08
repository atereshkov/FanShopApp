package com.github.handioq.fanshop.di.component;

import com.github.handioq.fanshop.cart.CartFragment;
import com.github.handioq.fanshop.di.module.CartModule;
import com.github.handioq.fanshop.di.scope.UserScope;

import dagger.Component;

@UserScope
@Component(dependencies = NetComponent.class, modules = CartModule.class)
public interface CartComponent {

    void inject(CartFragment catalogFragment);

}