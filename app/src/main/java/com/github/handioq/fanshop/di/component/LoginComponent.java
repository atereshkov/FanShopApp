package com.github.handioq.fanshop.di.component;

import com.github.handioq.fanshop.di.module.LoginModule;
import com.github.handioq.fanshop.di.scope.UserScope;
import com.github.handioq.fanshop.login.LoginActivity;

import dagger.Component;

@UserScope
@Component(dependencies = NetComponent.class, modules = LoginModule.class)
public interface LoginComponent {

    void inject(LoginActivity loginActivity);

}