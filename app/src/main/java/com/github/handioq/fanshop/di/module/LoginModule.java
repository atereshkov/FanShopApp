package com.github.handioq.fanshop.di.module;

import com.github.handioq.fanshop.di.scope.PresenterScope;
import com.github.handioq.fanshop.ui.login.LoginMvp;
import com.github.handioq.fanshop.ui.login.LoginPresenter;
import com.github.handioq.fanshop.net.NetworkService;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginModule {

    @Provides
    @PresenterScope
    public LoginMvp.Presenter providesCatalogPresenter(NetworkService networkService) {
        return new LoginPresenter(networkService);
    }
}