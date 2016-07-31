package com.github.handioq.fanshop.di.module;

import com.github.handioq.fanshop.net.NetworkService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class NetModule {

    @Provides
    @Singleton
    NetworkService providesNetworkService() {
        return new NetworkService();
    }

}
