package com.github.handioq.fanshop.util;

import com.github.handioq.fanshop.model.dto.CategoryDTO;
import com.github.handioq.fanshop.model.dto.ImageDTO;
import com.github.handioq.fanshop.model.dto.ProductDTO;
import com.github.handioq.fanshop.model.dto.ReviewDTO;
import com.github.handioq.fanshop.model.dto.SubcategoryDTO;
import com.github.handioq.fanshop.model.dvo.CategoryDVO;
import com.github.handioq.fanshop.model.dvo.ImageDVO;
import com.github.handioq.fanshop.model.dvo.ProductDVO;
import com.github.handioq.fanshop.model.dvo.ReviewDVO;
import com.github.handioq.fanshop.model.dvo.SubcategoryDVO;

import java.util.ArrayList;
import java.util.List;

public class Mapper {

    public static List<ProductDVO> mapProductsToDvo(List<ProductDTO> productsDTO) {
        List<ProductDVO> productsDVO = new ArrayList<>(productsDTO.size());
        for (ProductDTO productDTO : productsDTO) {
            productsDVO.add(mapProductToDvo(productDTO));
        }

        return productsDVO;
    }

    private static ImageDVO mapImageToDvo(ImageDTO imageDTO) {
        return new ImageDVO(imageDTO.getImage());
    }

    private static ReviewDVO mapReviewToDvo(ReviewDTO reviewDTO) {
        return new ReviewDVO(reviewDTO.getMessage(), reviewDTO.getStars());
    }

    private static ProductDVO mapProductToDvo(ProductDTO productDTO) {
        List<ImageDVO> imagesDVO = mapImagesList(productDTO);
        List<ReviewDVO> reviewsDVO = mapReviewsList(productDTO);

        return new ProductDVO(productDTO.getId(), productDTO.getName(),
                productDTO.getPrice(), productDTO.getDescription(),
                productDTO.isUserFavorite(), productDTO.getImageUrl(),
                imagesDVO, reviewsDVO);
    }

    private static List<ImageDVO> mapImagesList(ProductDTO productDTO) {
        List<ImageDVO> imagesDVO = new ArrayList<>(productDTO.getImages().size());
        for (ImageDTO imageDTO : productDTO.getImages()) {
            imagesDVO.add(mapImageToDvo(imageDTO));
        }
        return imagesDVO;
    }

    private static List<ReviewDVO> mapReviewsList(ProductDTO productDTO) {
        List<ReviewDVO> reviewsDVO = new ArrayList<>(productDTO.getReviews().size());
        for (ReviewDTO reviewDTO : productDTO.getReviews()) {
            reviewsDVO.add(mapReviewToDvo(reviewDTO));
        }
        return reviewsDVO;
    }

    public static List<CategoryDVO> mapCategoriesToDvo(List<CategoryDTO> categoriesDTO) {
        List<CategoryDVO> categoryDVO = new ArrayList<>(categoriesDTO.size());
        for (CategoryDTO categoryDTO : categoriesDTO) {
            categoryDVO.add(mapCategoryToDvo(categoryDTO));
        }

        return categoryDVO;
    }

    public static CategoryDVO mapCategoryToDvo(CategoryDTO categoryDTO) {
        List<SubcategoryDVO> subcategoriesDVO = mapSubcategories(categoryDTO);

        return new CategoryDVO(categoryDTO.getId(), categoryDTO.getName(),
                categoryDTO.getImageUrl(), subcategoriesDVO);
    }

    private static List<SubcategoryDVO> mapSubcategories(CategoryDTO categoryDTO) {
        List<SubcategoryDVO> subcategoriesDVO = new ArrayList<>(categoryDTO.getSubcategories().size());
        for (SubcategoryDTO subcategoryDTO : categoryDTO.getSubcategories()) {
            subcategoriesDVO.add(mapSubcategoryToDvo(subcategoryDTO));
        }
        return subcategoriesDVO;
    }

    private static SubcategoryDVO mapSubcategoryToDvo(SubcategoryDTO subcategoryDTO) {
        return new SubcategoryDVO(subcategoryDTO.getId(), subcategoryDTO.getName(), subcategoryDTO.getImageUrl());
    }

}
