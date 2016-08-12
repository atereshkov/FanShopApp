package com.github.handioq.fanshop.categories.subcategory;

import com.github.handioq.fanshop.base.Mvp;
import com.github.handioq.fanshop.model.dto.CategoryDTO;

public interface SubcategoryMvp {

    interface Model extends Mvp.Model {

        void getCategory(int categoryId);

        void setCallback(Callback callback);

        interface Callback {

            void onCategoryLoaded(CategoryDTO category);

            void onCategoryLoadError(Throwable error);

            void onLoadCategoryCompleted();
        }
    }

    interface View extends Mvp.View {

        void showLoadCategoryProgress();

        void hideLoadCategoryProgress();

        void setCategory(CategoryDTO category);

        void showLoadCategoryError(Throwable e);

    }

    interface Presenter extends Mvp.Presenter<SubcategoryMvp.View> {

        void getCategory(int categoryId);

    }
}
