package com.github.handioq.fanshop.di.component;

import com.github.handioq.fanshop.di.module.SignupModule;
import com.github.handioq.fanshop.di.scope.PresenterScope;
import com.github.handioq.fanshop.ui.signup.SignupActivity;

import dagger.Component;

@PresenterScope
@Component(dependencies = NetComponent.class, modules = SignupModule.class)
public interface SignupComponent {

    void inject(SignupActivity signupActivity);

}