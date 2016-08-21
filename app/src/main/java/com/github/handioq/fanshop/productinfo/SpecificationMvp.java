package com.github.handioq.fanshop.productinfo;

import com.github.handioq.fanshop.base.Mvp;
import com.github.handioq.fanshop.model.dto.SpecificationDTO;

public interface SpecificationMvp {

    interface Model extends Mvp.Model {

        void getSpecification(int id);

        void setCallback(Callback callback);

        interface Callback {

            void onSpecificationLoaded(SpecificationDTO specification);

            void onSpecificationLoadError(Throwable error);

            void onSpecificationLoadCompleted();
        }
    }

    interface View extends Mvp.View {

        void showProgress();

        void hideProgress();

        void setSpecification(SpecificationDTO specification);

        void onError(Throwable e);

    }

    interface Presenter extends Mvp.Presenter<SpecificationMvp.View> {

        void getSpecification(int id);

    }
}