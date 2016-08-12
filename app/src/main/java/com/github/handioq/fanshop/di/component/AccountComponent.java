package com.github.handioq.fanshop.di.component;

import com.github.handioq.fanshop.account.AccountFragment;
import com.github.handioq.fanshop.di.module.AccountModule;
import com.github.handioq.fanshop.di.scope.PresenterScope;

import dagger.Component;

@PresenterScope
@Component(dependencies = NetComponent.class, modules = AccountModule.class)
public interface AccountComponent {

    void inject(AccountFragment accountFragment);

}