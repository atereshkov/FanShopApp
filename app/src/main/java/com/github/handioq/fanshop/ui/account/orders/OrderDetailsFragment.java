package com.github.handioq.fanshop.ui.account.orders;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.handioq.R;
import com.github.handioq.fanshop.application.FanShopApp;
import com.github.handioq.fanshop.base.BaseFragment;
import com.github.handioq.fanshop.model.dvo.OrderDetailsDVO;
import com.github.handioq.fanshop.model.dvo.ProductDVO;
import com.github.handioq.fanshop.ui.account.orders.adapter.OrderDetailsRecyclerAdapter;
import com.github.handioq.fanshop.util.AuthPreferences;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class OrderDetailsFragment extends BaseFragment implements OrderDetailsMvp.View {

    private final static String TAG = "OrderDetailsFragment";
    private final static String ARGUMENT_ORDER_ID = "order_id";

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

    @Inject
    OrderDetailsMvp.Presenter orderDetailsPresenter;

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

        orderDetailsPresenter.setView(this);
        orderDetailsPresenter.getOrderDetails(authPreferences.getUserId(), orderId);
    }

    private void initRecyclerView(List<ProductDVO> products)
    {
        adapter = new OrderDetailsRecyclerAdapter(products);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
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
        getActivity().setTitle(getString(R.string.order_details_id, orderDetails.getId()));

        idView.setText(getString(R.string.order_details_id, orderDetails.getId()));
        statusView.setText(getString(R.string.order_details_status, orderDetails.getStatus()));
        dateView.setText(getString(R.string.order_details_date, orderDetails.getDate()));
    }

    @Override
    public void onError(Throwable e) {
        Log.e(TAG, e.toString());
    }
}
