package com.github.handioq.fanshop.application;

import android.app.Application;

import com.github.handioq.BuildConfig;
import com.github.handioq.application.MyEventBusIndex;
import com.github.handioq.fanshop.di.component.AccountComponent;
import com.github.handioq.fanshop.di.component.CartComponent;
import com.github.handioq.fanshop.di.component.CatalogComponent;
import com.github.handioq.fanshop.di.component.CategoriesComponent;
import com.github.handioq.fanshop.di.component.DaggerAccountComponent;
import com.github.handioq.fanshop.di.component.DaggerCartComponent;
import com.github.handioq.fanshop.di.component.DaggerCatalogComponent;
import com.github.handioq.fanshop.di.component.DaggerCategoriesComponent;
import com.github.handioq.fanshop.di.component.DaggerDatabaseComponent;
import com.github.handioq.fanshop.di.component.DaggerLoginComponent;
import com.github.handioq.fanshop.di.component.DaggerNetComponent;
import com.github.handioq.fanshop.di.component.DaggerProductInfoComponent;
import com.github.handioq.fanshop.di.component.DaggerSignupComponent;
import com.github.handioq.fanshop.di.component.DatabaseComponent;
import com.github.handioq.fanshop.di.component.LoginComponent;
import com.github.handioq.fanshop.di.component.NetComponent;
import com.github.handioq.fanshop.di.component.ProductInfoComponent;
import com.github.handioq.fanshop.di.component.SignupComponent;
import com.github.handioq.fanshop.di.module.AppModule;
import com.github.handioq.fanshop.di.module.DatabaseModule;

import org.greenrobot.eventbus.EventBus;

import timber.log.Timber;

public class FanShopApp extends Application {

    private NetComponent netComponent;
    private DatabaseComponent databaseComponent;
    private CatalogComponent catalogComponent;
    private LoginComponent loginComponent;
    private SignupComponent signupComponent;
    private ProductInfoComponent productInfoComponent;
    private CartComponent cartComponent;
    private AccountComponent accountComponent;
    private CategoriesComponent categoriesComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        netComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                //.netModule(new NetModule())
                .build();

        databaseComponent = DaggerDatabaseComponent.builder()
                .appModule(new AppModule(this))
                .databaseModule(new DatabaseModule(this))
                .build();

        catalogComponent = DaggerCatalogComponent.builder()
                .netComponent(netComponent)
                .build();

        loginComponent = DaggerLoginComponent.builder()
                .netComponent(netComponent)
                .build();

        signupComponent = DaggerSignupComponent.builder()
                .netComponent(netComponent)
                .build();

        productInfoComponent = DaggerProductInfoComponent.builder()
                .netComponent(netComponent)
                .build();

        cartComponent = DaggerCartComponent.builder()
                .netComponent(netComponent)
                .build();

        accountComponent = DaggerAccountComponent.builder()
                .netComponent(netComponent)
                .build();

        categoriesComponent = DaggerCategoriesComponent.builder()
                .netComponent(netComponent)
                .build();

        EventBus.builder()
                .addIndex(new MyEventBusIndex())
                .installDefaultEventBus();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    public NetComponent getNetComponent() {
        return netComponent;
    }

    public DatabaseComponent getDatabaseComponent() {
        return databaseComponent;
    }

    public CatalogComponent getCatalogComponent() {
        return catalogComponent;
    }

    public LoginComponent getLoginComponent() {
        return loginComponent;
    }

    public SignupComponent getSignupComponent() {
        return signupComponent;
    }

    public ProductInfoComponent getProductInfoComponent() {
        return productInfoComponent;
    }

    public CartComponent getCartComponent() {
        return cartComponent;
    }

    public AccountComponent getAccountComponent() {
        return accountComponent;
    }

    public CategoriesComponent getCategoriesComponent() {
        return categoriesComponent;
    }
}