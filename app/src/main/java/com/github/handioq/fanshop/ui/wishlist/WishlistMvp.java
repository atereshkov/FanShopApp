package com.github.handioq.fanshop.ui.wishlist;

import com.github.handioq.fanshop.base.Mvp;
import com.github.handioq.fanshop.model.dto.ProductDTO;
import com.github.handioq.fanshop.model.dvo.ProductDVO;
import com.github.handioq.fanshop.model.dvo.ProductListDVO;

import java.util.List;

public interface WishlistMvp {

    interface Model extends Mvp.Model {

        void getWishlist(int userId);

        void setCallback(Callback callback);

        interface Callback {

            void onWishlistLoaded(ProductListDVO products);

            void onWishlistLoadError(Throwable error);

            void onLoadWishlistCompleted();
        }
    }

    interface View extends Mvp.View {

        void showLoadWishlistProgress();

        void hideLoadWishlistProgress();

        void setWishlist(ProductListDVO products);

        void showLoadWishlistError(Throwable e); // change to showLoadProductsError(int errorCode);

    }

    interface Presenter extends Mvp.Presenter<WishlistMvp.View> {

        void getWishlist(int userId);

    }
}
