package com.github.handioq.fanshop.di.component;

import android.content.Context;

import com.github.handioq.fanshop.database.ProductRepository;
import com.github.handioq.fanshop.di.module.AppModule;
import com.github.handioq.fanshop.di.module.DatabaseModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, DatabaseModule.class})
public interface DatabaseComponent {

    void inject(ProductRepository productRepository);

    Context applicationContext();

}