package com.github.handioq.fanshop.account;

import com.github.handioq.fanshop.base.Mvp;
import com.github.handioq.fanshop.model.dto.OrderDTO;

import java.util.List;

public interface AccountMvp {

    interface Model extends Mvp.Model {

        void gerOrders(int userId);

        void setCallback(Callback callback);

        interface Callback {

            void onOrdersLoaded(List<OrderDTO> orderDTOs);

            void onOrdersLoadError(Throwable error);

            void onCompleted();
        }
    }

    interface View extends Mvp.View {

        void showProgress();

        void hideProgress();

        void setOrders(List<OrderDTO> orders);

        void onError(Throwable e);

        //void onItemClicked(View view, int position);

    }

    interface Presenter extends Mvp.Presenter<AccountMvp.View> {

        void getOrders(int userId);

    }

}
