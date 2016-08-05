package com.github.handioq.fanshop.di.module;

import com.github.handioq.fanshop.di.scope.UserScope;
import com.github.handioq.fanshop.login.LoginPresenter;
import com.github.handioq.fanshop.login.LoginPresenterImpl;
import com.github.handioq.fanshop.net.NetworkService;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginModule {

    @Provides
    @UserScope
    public LoginPresenter providesCatalogPresenter(NetworkService networkService) {
        return new LoginPresenterImpl(networkService);
    }
}