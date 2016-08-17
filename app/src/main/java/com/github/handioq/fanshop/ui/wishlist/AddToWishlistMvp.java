package com.github.handioq.fanshop.ui.wishlist;

import com.github.handioq.fanshop.base.Mvp;
import com.github.handioq.fanshop.model.dto.ProductDTO;
import com.github.handioq.fanshop.net.model.Response;

public interface AddToWishlistMvp {

    interface Model extends Mvp.Model {

        void addProductToWishlist(int userId, ProductDTO product);

        void setCallback(Callback callback);

        interface Callback {

            void onProductAddedToWishlist(Response response);

            void onWishlistAddError(Throwable error);

            void onAddToWishlistCompleted();
        }
    }

    interface View extends Mvp.View {

        void onProductAddedToWishlist(Response response);

        void onWishlistAddError(Throwable e);

    }

    interface Presenter extends Mvp.Presenter <AddToWishlistMvp.View> {

        void addProductToWishlist(int userId, ProductDTO product);

    }
}
