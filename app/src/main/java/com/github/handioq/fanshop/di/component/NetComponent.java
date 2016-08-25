package com.github.handioq.fanshop.di.component;

import com.github.handioq.fanshop.di.module.AppModule;
import com.github.handioq.fanshop.di.module.NetModule;
import com.github.handioq.fanshop.net.NetworkService;
import com.github.handioq.fanshop.util.AuthPreferences;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {

    NetworkService networkService();

    AuthPreferences authPreferences();

}