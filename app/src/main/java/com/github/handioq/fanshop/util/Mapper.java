package com.github.handioq.fanshop.util;

import android.support.annotation.NonNull;

import com.github.handioq.fanshop.model.dto.ImageDTO;
import com.github.handioq.fanshop.model.dto.ProductDTO;
import com.github.handioq.fanshop.model.dto.ReviewDTO;
import com.github.handioq.fanshop.model.dvo.ImageDVO;
import com.github.handioq.fanshop.model.dvo.ProductDVO;
import com.github.handioq.fanshop.model.dvo.ReviewDVO;

import java.util.ArrayList;
import java.util.List;

public class Mapper {

    private static ImageDVO mapDtoToDvo(ImageDTO imageDTO) {
        return new ImageDVO(imageDTO.getImage());
    }

    private static ReviewDVO mapDtoToDvo(ReviewDTO reviewDTO) {
        return new ReviewDVO(reviewDTO.getMessage(), reviewDTO.getStars());
    }

    private static ProductDVO mapDtoToDvo(ProductDTO productDTO) {
        List<ImageDVO> imagesDVO = mapImagesList(productDTO);
        List<ReviewDVO> reviewsDVO = mapReviewsList(productDTO);

        return new ProductDVO(productDTO.getId(), productDTO.getName(),
                productDTO.getPrice(), productDTO.getDescription(),
                productDTO.isUserFavorite(), productDTO.getImageUrl(),
                imagesDVO, reviewsDVO);
    }

    public static List<ProductDVO> mapDtoToDvo(List<ProductDTO> productsDTO) {
        List<ProductDVO> productsDVO = new ArrayList<>(productsDTO.size());
        for(ProductDTO productDTO : productsDTO) {
            productsDVO.add(mapDtoToDvo(productDTO));
        }

        return productsDVO;
    }

    @NonNull
    private static List<ImageDVO> mapImagesList(ProductDTO productDTO) {
        List<ImageDVO> imagesDVO = new ArrayList<>(productDTO.getImages().size());
        for (ImageDTO imageDTO : productDTO.getImages()) {
            imagesDVO.add(mapDtoToDvo(imageDTO));
        }
        return imagesDVO;
    }

    @NonNull
    public static List<ReviewDVO> mapReviewsList(ProductDTO productDTO) {
        List<ReviewDVO> reviewsDVO = new ArrayList<>(productDTO.getReviews().size());
        for (ReviewDTO reviewDTO : productDTO.getReviews()) {
            reviewsDVO.add(mapDtoToDvo(reviewDTO));
        }
        return reviewsDVO;
    }
}
