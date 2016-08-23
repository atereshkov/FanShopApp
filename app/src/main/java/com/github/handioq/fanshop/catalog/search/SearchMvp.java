package com.github.handioq.fanshop.catalog.search;

import com.github.handioq.fanshop.base.Mvp;
import com.github.handioq.fanshop.model.dto.ProductDTO;
import com.github.handioq.fanshop.model.dvo.ProductDVO;

import java.util.List;

public interface SearchMvp {

    interface Model extends Mvp.Model {

        void search(String query, int offset, int limit);

        void setCallback(Callback callback);

        interface Callback {

            void onSearchSuccess(List<ProductDVO> products);

            void onSearchError(Throwable error);

            void onSearchCompleted();
        }
    }

    interface View extends Mvp.View {

        void onSearchSuccess(List<ProductDVO> products);

        void onSearchError(Throwable e);

        void showSearchProgress();

        void hideSearchProgress();

    }

    interface Presenter extends Mvp.Presenter<SearchMvp.View> {

        void search(String query, int offset, int limit);

    }
}
