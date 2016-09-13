package com.github.handioq.fanshop.ui.checkout;

import com.github.handioq.fanshop.base.Mvp;
import com.github.handioq.fanshop.model.dto.OrderDTO;
import com.github.handioq.fanshop.model.dto.ProductDTO;
import com.github.handioq.fanshop.model.dto.ProductIdDTO;
import com.github.handioq.fanshop.net.model.Response;

import java.util.List;

public interface CheckoutMvp {

    interface Model extends Mvp.Model {

        void createOrder(int userId, List<ProductIdDTO> products);

        void setCallback(Callback callback);

        interface Callback {

            void onOrderCreated(Response response);

            void onOrderCreateError(Throwable error);

            void onOrderCompleted();
        }
    }

    interface View extends Mvp.View {

        void showProgress();

        void hideProgress();

        void setResponse(Response response);

        void onError(Throwable e);

    }

    interface Presenter extends Mvp.Presenter<CheckoutMvp.View> {

        void createOrder(int userId, List<ProductIdDTO> products);

    }

}
