package com.github.handioq.fanshop.di.component;

import com.github.handioq.fanshop.di.module.WishlistModule;
import com.github.handioq.fanshop.di.scope.PresenterScope;
import com.github.handioq.fanshop.ui.wishlist.WishlistFragment;

import dagger.Component;

@PresenterScope
@Component(dependencies = NetComponent.class, modules = WishlistModule.class)
public interface WishlistComponent {

    void inject(WishlistFragment wishlistFragment);

}