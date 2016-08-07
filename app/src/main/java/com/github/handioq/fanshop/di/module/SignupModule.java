package com.github.handioq.fanshop.di.module;

import com.github.handioq.fanshop.di.scope.UserScope;
import com.github.handioq.fanshop.net.NetworkService;
import com.github.handioq.fanshop.signup.SignupMvp;
import com.github.handioq.fanshop.signup.SignupPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class SignupModule {

    @Provides
    @UserScope
    public SignupMvp.Presenter providesSignupPresenter(NetworkService networkService) {
        return new SignupPresenter(networkService);
    }
}