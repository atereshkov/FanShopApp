package com.github.handioq.fanshop.ui.checkout;

import com.github.handioq.fanshop.model.dto.OrderDTO;
import com.github.handioq.fanshop.net.model.Response;

public class CheckoutPresenter implements CheckoutMvp.Presenter, CheckoutMvp.Model.Callback {

    @Override
    public void onOrderCreated(Response response) {

    }

    @Override
    public void onOrderCreateError(Throwable error) {

    }

    @Override
    public void onOrderCompleted() {

    }

    @Override
    public void createOrder(int id, OrderDTO order) {

    }

    @Override
    public void setView(CheckoutMvp.View view) {

    }
}
