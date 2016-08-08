package com.github.handioq.fanshop.di.module;

import com.github.handioq.fanshop.account.AccountMvp;
import com.github.handioq.fanshop.account.AccountPresenter;
import com.github.handioq.fanshop.di.scope.UserScope;
import com.github.handioq.fanshop.net.NetworkService;

import dagger.Module;
import dagger.Provides;

@Module
public class AccountModule {

    @Provides
    @UserScope
    public AccountMvp.Presenter providesAccountPresenter(NetworkService networkService) {
        return new AccountPresenter(networkService);
    }
}