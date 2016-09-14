package com.github.handioq.fanshop.ui.account.orders.iteraction;

import com.github.handioq.fanshop.base.Mvp;
import com.github.handioq.fanshop.model.dto.ReviewDTO;
import com.github.handioq.fanshop.net.model.Response;

public interface PayMvp {

    interface Model extends Mvp.Model {

        void paymentForOrder(int userId, int orderId);

        void setCallback(Callback callback);

        interface Callback {

            void onPaySuccess(Response response);

            void onPayError(Throwable error);

            void onPayCompleted();
        }
    }

    interface View extends Mvp.View {

        void onPaySuccess(Response response);

        void onPayError(Throwable e);

    }

    interface Presenter extends Mvp.Presenter <PayMvp.View> {

        void paymentForOrder(int userId, int orderId);

    }

}
