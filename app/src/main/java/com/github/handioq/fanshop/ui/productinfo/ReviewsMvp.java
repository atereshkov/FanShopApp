package com.github.handioq.fanshop.ui.productinfo;

import com.github.handioq.fanshop.base.Mvp;
import com.github.handioq.fanshop.model.dvo.ReviewDVO;
import com.github.handioq.fanshop.model.dvo.ReviewListDVO;

import java.util.List;

public interface ReviewsMvp {

    interface Model extends Mvp.Model {

        void getReviews(int id);

        void setCallback(Callback callback);

        interface Callback {

            void onReviewsLoaded(ReviewListDVO reviews);

            void onReviewsLoadError(Throwable error);

            void onReviewsLoadCompleted();
        }
    }

    interface View extends Mvp.View {

        void showProgress();

        void hideProgress();

        void setReviews(ReviewListDVO reviews);

        void onError(Throwable e);

    }

    interface Presenter extends Mvp.Presenter<ReviewsMvp.View> {

        void getReviews(int id);

    }
}
