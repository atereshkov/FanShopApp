package com.github.handioq.fanshop.di.component;

import com.github.handioq.fanshop.di.module.CheckoutModule;
import com.github.handioq.fanshop.di.scope.PresenterScope;
import com.github.handioq.fanshop.ui.checkout.CheckoutFragment;

import dagger.Component;

@PresenterScope
@Component(dependencies = NetComponent.class, modules = CheckoutModule.class)
public interface CheckoutComponent {

    void inject(CheckoutFragment checkoutFragment);

}