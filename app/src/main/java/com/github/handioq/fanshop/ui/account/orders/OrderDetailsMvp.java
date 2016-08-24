package com.github.handioq.fanshop.ui.account.orders;

import com.github.handioq.fanshop.base.Mvp;
import com.github.handioq.fanshop.model.dvo.OrderDetailsDVO;

public interface OrderDetailsMvp {

    interface Model extends Mvp.Model {

        void getOrderDetails(int orderId);

        void setCallback(Callback callback);

        interface Callback {

            void onDetailsLoaded(OrderDetailsDVO orderDetails);

            void onDetailsLoadError(Throwable error);
        }
    }

    interface View extends Mvp.View {

        void showProgress();

        void hideProgress();

        void setOrderDetails(OrderDetailsDVO orderDetails);

        void onError(Throwable e);

    }

    interface Presenter extends Mvp.Presenter<OrderDetailsMvp.View> {

        void getOrderDetails(int orderId);

    }

}
