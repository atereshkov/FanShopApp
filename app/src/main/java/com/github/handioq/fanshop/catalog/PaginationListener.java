package com.github.handioq.fanshop.catalog;

public interface PaginationListener {

    void onPaginationLoad(boolean state, int totalItemCount, int limit);

}
