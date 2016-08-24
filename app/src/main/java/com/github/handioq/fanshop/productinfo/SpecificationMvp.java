package com.github.handioq.fanshop.productinfo;

import com.github.handioq.fanshop.base.Mvp;
import com.github.handioq.fanshop.model.dvo.SpecificationDVO;

public interface SpecificationMvp {

    interface Model extends Mvp.Model {

        void getSpecification(int id);

        void setCallback(Callback callback);

        interface Callback {

            void onSpecificationLoaded(SpecificationDVO specification);

            void onSpecificationLoadError(Throwable error);

            void onSpecificationLoadCompleted();
        }
    }

    interface View extends Mvp.View {

        void showProgress();

        void hideProgress();

        void setSpecification(SpecificationDVO specification);

        void onError(Throwable e);

    }

    interface Presenter extends Mvp.Presenter<SpecificationMvp.View> {

        void getSpecification(int id);

    }
}