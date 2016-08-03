package com.github.handioq.fanshop.di.component;

import com.github.handioq.fanshop.catalog.CatalogFragment;
import com.github.handioq.fanshop.di.module.CatalogModule;
import com.github.handioq.fanshop.di.module.SignupModule;
import com.github.handioq.fanshop.di.scope.UserScope;
import com.github.handioq.fanshop.signup.SignupActivity;

import dagger.Component;

@UserScope
@Component(dependencies = NetComponent.class, modules = SignupModule.class)
public interface SignupComponent {

    void inject(SignupActivity signupActivity);

}