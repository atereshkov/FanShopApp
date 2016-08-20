package com.github.handioq.fanshop.ui.wishlist.interaction;

import com.github.handioq.fanshop.base.Mvp;

public interface RemoveWishlistMvp {

    interface Model extends Mvp.Model {

        void removeProduct(int userId, int productId);

        void setCallback(Callback callback);

        interface Callback {

            void onProductRemovedFromWishlist();

            void onWishlistRemoveError(Throwable error);

            void onRemoveFromWishlistCompleted();
        }
    }

    interface View extends Mvp.View {

        void onProductRemovedFromWishlist();

        void onWishlistRemoveError(Throwable e);

    }

    interface Presenter extends Mvp.Presenter <RemoveWishlistMvp.View> {

        void removeProduct(int userId, int productId);

    }
}
