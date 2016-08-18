package com.github.handioq.fanshop.cart;

import com.github.handioq.fanshop.base.Mvp;

public interface RemoveFromCartMvp {

    interface Model extends Mvp.Model {

        void removeProduct(int userId, int productId);

        void setCallback(Callback callback);

        interface Callback {

            void onProductRemoved();

            void onProductRemoveError(Throwable error);

            void onRemoveCompleted();
        }
    }

    interface View extends Mvp.View {

        void onProductRemoveSuccess();

        void onProductRemoveError(Throwable e);

    }

    interface Presenter extends Mvp.Presenter <RemoveFromCartMvp.View> {

        void removeProductFromCart(int userId, int productId);

    }
}
