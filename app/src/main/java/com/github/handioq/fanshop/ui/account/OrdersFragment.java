package com.github.handioq.fanshop.ui.account;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.github.handioq.R;
import com.github.handioq.fanshop.application.FanShopApp;
import com.github.handioq.fanshop.base.BaseFragment;
import com.github.handioq.fanshop.model.dvo.OrderDVO;
import com.github.handioq.fanshop.model.dvo.OrderListDVO;
import com.github.handioq.fanshop.ui.account.adapter.OrderRecyclerAdapter;
import com.github.handioq.fanshop.util.AuthPreferences;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;

public class OrdersFragment extends BaseFragment implements OrderMvp.View {

    private final static String TAG = "OrdersFragment";

    private LinearLayoutManager layoutManager;
    private OrderRecyclerAdapter adapter;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Inject
    OrderMvp.Presenter ordersPresenter;

    @Inject
    AuthPreferences authPreferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_orders, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.i(TAG, "onViewCreated");

        ((FanShopApp) getContext().getApplicationContext()).getAccountComponent().inject(this);

        adapter = new OrderRecyclerAdapter(new ArrayList<OrderDVO>());

        ordersPresenter.setView(this);
        ordersPresenter.getOrders(authPreferences.getUserId());

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void setOrders(OrderListDVO orders) {
        adapter.setOrders(orders.getOrders());
    }

    @Override
    public void onError(Throwable e) {
        Log.i(TAG, e.toString());
    }
}
