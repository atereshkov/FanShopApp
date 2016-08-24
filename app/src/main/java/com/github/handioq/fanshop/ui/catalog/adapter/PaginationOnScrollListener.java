package com.github.handioq.fanshop.ui.catalog.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.github.handioq.fanshop.ui.catalog.PaginationListener;
import com.github.handioq.fanshop.util.NetworkConstants;

public class PaginationOnScrollListener extends RecyclerView.OnScrollListener {

    private static final String TAG = "PaginationOnScroll";

    private LinearLayoutManager layoutManager;
    private PaginationListener paginationListener;

    boolean loading = true;
    private int previousTotal = 0;

    public PaginationOnScrollListener(PaginationListener paginationListener, LinearLayoutManager layoutManager) {
        this.paginationListener = paginationListener;
        this.layoutManager = layoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        int visibleItemCount = recyclerView.getChildCount();
        int totalItemCount = layoutManager.getItemCount();
        int firstVisibleItem = layoutManager.findFirstVisibleItemPosition();

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
            }
        }

        if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem)) {
            Log.i(TAG, "end onScroll");

            paginationListener.onPaginationLoad(loading, totalItemCount, NetworkConstants.PRODUCTS_LOAD_COUNT);

            loading = true;
        }
    }
}


