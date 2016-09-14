package com.github.handioq.fanshop.ui.catalog.search;

import com.github.handioq.fanshop.base.Mvp;
import com.github.handioq.fanshop.model.dvo.ProductDVO;
import com.github.handioq.fanshop.model.dvo.ProductListDVO;

import java.util.List;
import java.util.Map;

public interface SearchMvp {

    interface Model extends Mvp.Model {

        void search(Map<String, String> options, int offset, int limit);

        void setCallback(Callback callback);

        interface Callback {

            void onSearchSuccess(ProductListDVO products);

            void onSearchError(Throwable error);

            void onSearchCompleted();
        }
    }

    interface View extends Mvp.View {

        void onSearchSuccess(ProductListDVO products);

        void onSearchError(Throwable e);

        void showSearchProgress();

        void hideSearchProgress();

    }

    interface Presenter extends Mvp.Presenter<SearchMvp.View> {

        void search(Map<String, String> options, int offset, int limit);

    }
}
