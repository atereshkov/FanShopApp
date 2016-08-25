package com.github.handioq.fanshop.di.module;

import com.github.handioq.fanshop.di.scope.PresenterScope;
import com.github.handioq.fanshop.net.NetworkService;
import com.github.handioq.fanshop.ui.signup.SignupMvp;
import com.github.handioq.fanshop.ui.signup.SignupPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class SignupModule {

    @Provides
    @PresenterScope
    public SignupMvp.Presenter providesSignupPresenter(NetworkService networkService) {
        return new SignupPresenter(networkService);
    }
}