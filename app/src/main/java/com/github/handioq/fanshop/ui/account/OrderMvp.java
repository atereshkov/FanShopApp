package com.github.handioq.fanshop.ui.account;

import com.github.handioq.fanshop.base.Mvp;
import com.github.handioq.fanshop.model.dvo.OrderDVO;
import com.github.handioq.fanshop.model.dvo.OrderListDVO;

import java.util.List;

public interface OrderMvp {

    interface Model extends Mvp.Model {

        void getOrders(int userId);

        void setCallback(Callback callback);

        interface Callback {

            void onOrdersLoaded(OrderListDVO orders);

            void onOrdersLoadError(Throwable error);

            void onCompleted();
        }
    }

    interface View extends Mvp.View {

        void showProgress();

        void hideProgress();

        void setOrders(OrderListDVO orders);

        void onError(Throwable e);

        //void onItemClicked(View view, int position);

    }

    interface Presenter extends Mvp.Presenter<OrderMvp.View> {

        void getOrders(int userId);

    }
}
