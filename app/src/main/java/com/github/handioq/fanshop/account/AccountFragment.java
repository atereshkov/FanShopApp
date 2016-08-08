package com.github.handioq.fanshop.account;

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
import com.github.handioq.fanshop.account.adapter.AccountRecyclerAdapter;
import com.github.handioq.fanshop.application.FanShopApp;
import com.github.handioq.fanshop.base.BaseFragment;
import com.github.handioq.fanshop.cart.CartMvp;
import com.github.handioq.fanshop.model.dto.OrderDTO;
import com.github.handioq.fanshop.model.dto.ProductDTO;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class AccountFragment extends BaseFragment implements AccountMvp.View {

    private final static String TAG = "AccountFragment";

    private LinearLayoutManager layoutManager;
    private AccountRecyclerAdapter adapter;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Inject
    AccountMvp.Presenter accountPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // retain this fragment
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.i(TAG, "onViewCreated");

        ((FanShopApp) getContext().getApplicationContext()).getAccountComponent().inject(this);

        adapter = new AccountRecyclerAdapter(new ArrayList<OrderDTO>());

        accountPresenter.setView(this);
        accountPresenter.getOrders(575); // TODO CHANGE TO REAL ID

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
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
    public void setOrders(List<OrderDTO> orders) {
        adapter.setOrders(orders);
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        Log.i(TAG, "onError");
    }
}
