package com.github.handioq.fanshop.di.component;

import com.github.handioq.fanshop.ui.cart.CartFragment;
import com.github.handioq.fanshop.di.module.CartModule;
import com.github.handioq.fanshop.di.scope.PresenterScope;

import dagger.Component;

@PresenterScope
@Component(dependencies = NetComponent.class, modules = CartModule.class)
public interface CartComponent {

    void inject(CartFragment catalogFragment);

}