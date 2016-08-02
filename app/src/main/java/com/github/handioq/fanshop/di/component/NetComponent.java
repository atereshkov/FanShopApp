package com.github.handioq.fanshop.di.component;

import com.github.handioq.fanshop.catalog.CatalogFragment;
import com.github.handioq.fanshop.catalog.CatalogPresenterImpl;
import com.github.handioq.fanshop.di.module.AppModule;
import com.github.handioq.fanshop.di.module.NetModule;
import com.github.handioq.fanshop.login.LoginActivity;
import com.github.handioq.fanshop.net.NetworkService;
import com.github.handioq.fanshop.productinfo.ProductInfoFragment;
import com.github.handioq.fanshop.signup.SignupActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {

    void inject(CatalogFragment catalog);

    void inject(LoginActivity loginActivity);

    void inject(ProductInfoFragment productInfoFragment);

    void inject(SignupActivity signupActivity);

    NetworkService networkService();

}