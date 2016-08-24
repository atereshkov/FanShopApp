package com.github.handioq.fanshop.ui.categories.subcategory;

import com.github.handioq.fanshop.base.Mvp;
import com.github.handioq.fanshop.model.dvo.CategoryDVO;

public interface SubcategoryMvp {

    interface Model extends Mvp.Model {

        void getCategory(int categoryId);

        void setCallback(Callback callback);

        interface Callback {

            void onCategoryLoaded(CategoryDVO category);

            void onCategoryLoadError(Throwable error);

            void onLoadCategoryCompleted();
        }
    }

    interface View extends Mvp.View {

        void showLoadCategoryProgress();

        void hideLoadCategoryProgress();

        void setCategory(CategoryDVO category);

        void showLoadCategoryError(Throwable e);

    }

    interface Presenter extends Mvp.Presenter<SubcategoryMvp.View> {

        void getCategory(int categoryId);

    }
}
