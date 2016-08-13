package com.github.handioq.fanshop.productinfo;

import com.github.handioq.fanshop.base.Mvp;
import com.github.handioq.fanshop.model.dto.ReviewDTO;

import java.util.List;

public interface ReviewsInfoMvp {

    interface Model extends Mvp.Model {

        void getReviews(int id);

        void setCallback(Callback callback);

        interface Callback {

            void onReviewsLoaded(List<ReviewDTO> reviews);

            void onReviewsLoadError(Throwable error);

            void onReviewsLoadCompleted();
        }
    }

    interface View extends Mvp.View {

        void showProgress();

        void hideProgress();

        void setReviews(List<ReviewDTO> reviews);

        void onError(Throwable e);

    }

    interface Presenter extends Mvp.Presenter<ReviewsInfoMvp.View> {

        void getReviews(int id);

    }
}
