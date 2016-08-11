package com.github.handioq.fanshop.categories;

import com.github.handioq.fanshop.base.Mvp;
import com.github.handioq.fanshop.model.dto.CategoryDTO;

import java.util.List;

public interface CategoriesMvp {

    interface Model extends Mvp.Model {

        void getCategories();

        void setCallback(Callback callback);

        interface Callback {

            void onCategoriesLoaded(List<CategoryDTO> categories);

            void onCategoriesLoadError(Throwable error);

            void onLoadCategoriesCompleted();
        }
    }

    interface View extends Mvp.View {

        void showLoadCategoriesProgress();

        void hideLoadCategoriesProgress();

        void setCategories(List<CategoryDTO> categories);

        void showLoadCategoriesError(Throwable e); // change to showLoadProductsError(int errorCode);

    }

    interface Presenter extends Mvp.Presenter<CategoriesMvp.View> {

        void getCategories();

    }
}
