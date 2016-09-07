package com.github.handioq.fanshop.ui.categories;

import com.github.handioq.fanshop.base.Mvp;
import com.github.handioq.fanshop.model.dvo.CategoryDVO;
import com.github.handioq.fanshop.model.dvo.CategoryListDVO;

import java.util.List;

public interface CategoriesMvp {

    interface Model extends Mvp.Model {

        void getCategories();

        void setCallback(Callback callback);

        interface Callback {

            void onCategoriesLoaded(CategoryListDVO categories);

            void onCategoriesLoadError(Throwable error);

            void onLoadCategoriesCompleted();
        }
    }

    interface View extends Mvp.View {

        void showLoadCategoriesProgress();

        void hideLoadCategoriesProgress();

        void setCategories(CategoryListDVO categories);

        void showLoadCategoriesError(Throwable e); // change to showLoadProductsError(int errorCode);

    }

    interface Presenter extends Mvp.Presenter<CategoriesMvp.View> {

        void getCategories();

    }
}
