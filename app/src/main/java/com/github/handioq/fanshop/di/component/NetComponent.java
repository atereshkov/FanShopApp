package com.github.handioq.fanshop.di.component;

import com.github.handioq.fanshop.di.module.AppModule;
import com.github.handioq.fanshop.di.module.NetModule;
import com.github.handioq.fanshop.net.NetworkService;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {

    //void inject(LoginActivity loginActivity);

    NetworkService networkService();

}