package com.github.handioq.fanshop.di.component;

import com.github.handioq.fanshop.ui.account.OrdersFragment;
import com.github.handioq.fanshop.ui.account.UserFragment;
import com.github.handioq.fanshop.di.module.AccountModule;
import com.github.handioq.fanshop.di.scope.PresenterScope;
import com.github.handioq.fanshop.ui.account.orders.OrderDetailsFragment;

import dagger.Component;

@PresenterScope
@Component(dependencies = NetComponent.class, modules = AccountModule.class)
public interface AccountComponent {

    void inject(OrdersFragment ordersFragment);

    void inject(UserFragment userFragment);

    void inject(OrderDetailsFragment orderDetailsFragment);

}