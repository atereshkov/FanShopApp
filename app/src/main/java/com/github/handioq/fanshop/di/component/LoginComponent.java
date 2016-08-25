package com.github.handioq.fanshop.di.component;

import com.github.handioq.fanshop.di.module.LoginModule;
import com.github.handioq.fanshop.di.scope.PresenterScope;
import com.github.handioq.fanshop.ui.login.LoginActivity;

import dagger.Component;

@PresenterScope
@Component(dependencies = NetComponent.class, modules = LoginModule.class)
public interface LoginComponent {

    void inject(LoginActivity loginActivity);

}