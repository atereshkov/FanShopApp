package com.github.handioq.fanshop.ui.account.orders;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.handioq.R;
import com.github.handioq.fanshop.application.FanShopApp;
import com.github.handioq.fanshop.base.BaseFragment;
import com.github.handioq.fanshop.model.dvo.OrderDetailsDVO;
import com.github.handioq.fanshop.model.dvo.ProductDVO;
import com.github.handioq.fanshop.net.model.Response;
import com.github.handioq.fanshop.ui.account.orders.adapter.OrderDetailsRecyclerAdapter;
import com.github.handioq.fanshop.ui.account.orders.iteraction.PayMvp;
import com.github.handioq.fanshop.util.AuthPreferences;
import com.github.handioq.fanshop.util.DateUtils;
import com.github.handioq.fanshop.util.ErrorUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class OrderDetailsFragment extends BaseFragment implements OrderDetailsMvp.View, PayMvp.View {

    private final static String TAG = "OrderDetailsFragment";
    private final static String ARGUMENT_ORDER_ID = "order_id";
    private static final String AWAITING_PAYMENT_STATUS = "AWAIT";

    private LinearLayoutManager layoutManager;
    private OrderDetailsRecyclerAdapter adapter;
    private int orderId;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.text_id)
    TextView idView;

    @BindView(R.id.text_date)
    TextView dateView;

    @BindView(R.id.text_status)
    TextView statusView;

    @BindView(R.id.order_details_form)
    LinearLayout orderDetailsView;

    @BindView(R.id.pay_button)
    Button payButtonView; // TODO make icon

    @Inject
    OrderDetailsMvp.Presenter orderDetailsPresenter;

    @Inject
    PayMvp.Presenter payPresenter;

    @Inject
    AuthPreferences authPreferences;

    public static OrderDetailsFragment newInstance(int id) {
        OrderDetailsFragment fragment = new OrderDetailsFragment();

        Bundle args = new Bundle();
        args.putInt(ARGUMENT_ORDER_ID, id);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        orderId = this.getArguments().getInt(ARGUMENT_ORDER_ID);
        return inflater.inflate(R.layout.fragment_order_details, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "onViewCreated");

        ((FanShopApp) getContext().getApplicationContext()).getAccountComponent().inject(this);

        payPresenter.setView(this);
        orderDetailsPresenter.setView(this);
        orderDetailsPresenter.getOrderDetails(authPreferences.getUserId(), orderId);
    }

    private void initRecyclerView(List<ProductDVO> products) {
        adapter = new OrderDetailsRecyclerAdapter(products);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onPaySuccess(Response response) {
        Log.i(TAG, response.getStatusMessage());
        if (response.getStatusCode() == 200) {
            Toast.makeText(getContext(), response.getStatusMessage(), Toast.LENGTH_SHORT).show();
            orderDetailsPresenter.getOrderDetails(authPreferences.getUserId(), orderId);
        } else {
            Toast.makeText(getContext(), "Error: " + response.getStatusMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onPayError(Throwable e) {
        Log.e(TAG, e.toString());
        Toast.makeText(getContext(), ErrorUtils.getMessage(e), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        orderDetailsView.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
        orderDetailsView.setVisibility(View.VISIBLE);
    }

    @Override
    public void setOrderDetails(OrderDetailsDVO orderDetails) {
        initRecyclerView(orderDetails.getProducts());
        initPayButton(orderDetails);
        getActivity().setTitle(getString(R.string.order_details_id, orderDetails.getId()));

        idView.setText(getString(R.string.order_details_id, orderDetails.getId()));
        statusView.setText(getString(R.string.order_details_status, orderDetails.getStatus()));
        dateView.setText(getString(R.string.order_details_date, DateUtils.getStringDateFromLong(Long.parseLong(orderDetails.getDate()))));
    }

    @OnClick(R.id.pay_button)
    public void payClick() {
        payPresenter.paymentForOrder(authPreferences.getUserId(), orderId);
    }

    private void initPayButton(OrderDetailsDVO orderDetails) {
        if (orderDetails.getStatus().equals(AWAITING_PAYMENT_STATUS)) {
            payButtonView.setVisibility(View.VISIBLE);
        } else {
            payButtonView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onError(Throwable e) {
        Log.e(TAG, e.toString());
    }
}
