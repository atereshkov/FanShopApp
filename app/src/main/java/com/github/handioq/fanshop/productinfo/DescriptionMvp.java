package com.github.handioq.fanshop.productinfo;

import com.github.handioq.fanshop.base.Mvp;

public interface DescriptionMvp {

    interface Model extends Mvp.Model {

        void getDescription(int id);

        void setCallback(Callback callback);

        interface Callback {

            void onDescriptionLoaded(DescriptionDTO description);

            void onDescriptionLoadError(Throwable error);

            void onDescriptionLoadCompleted();
        }
    }

    interface View extends Mvp.View {

        void showProgress();

        void hideProgress();

        void setDescription(DescriptionDTO description);

        void onError(Throwable e);

    }

    interface Presenter extends Mvp.Presenter<ReviewsMvp.View> {

        void getDescription(int id);

    }
}
