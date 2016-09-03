package com.github.handioq.fanshop.ui.categories.subcategory;

import com.github.handioq.fanshop.base.Mvp;
import com.github.handioq.fanshop.model.dto.CategoryDTO;
import com.github.handioq.fanshop.model.dvo.CategoryDVO;

import java.util.List;

public interface SubcategoryMvp {

    interface Model extends Mvp.Model {

        void getCategory(int categoryId);

        void setCallback(Callback callback);

        interface Callback {

            void onCategoryLoaded(List<CategoryDVO> categories);

            void onCategoryLoadError(Throwable error);

            void onLoadCategoryCompleted();
        }
    }

    interface View extends Mvp.View {

        void showLoadCategoryProgress();

        void hideLoadCategoryProgress();

        void setCategory(List<CategoryDVO> categories);

        void showLoadCategoryError(Throwable e);

    }

    interface Presenter extends Mvp.Presenter<SubcategoryMvp.View> {

        void getCategory(int categoryId);

    }
}
