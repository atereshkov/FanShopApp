package com.github.handioq.fanshop.util;

import com.github.handioq.fanshop.model.dto.AddressDTO;
import com.github.handioq.fanshop.model.dto.CategoryDTO;
import com.github.handioq.fanshop.model.dto.ImageDTO;
import com.github.handioq.fanshop.model.dto.OrderDTO;
import com.github.handioq.fanshop.model.dto.OrderDetailsDTO;
import com.github.handioq.fanshop.model.dto.ProductDTO;
import com.github.handioq.fanshop.model.dto.ReviewDTO;
import com.github.handioq.fanshop.model.dto.SizeDTO;
import com.github.handioq.fanshop.model.dto.SpecificationDTO;
import com.github.handioq.fanshop.model.dto.UserDTO;
import com.github.handioq.fanshop.model.dvo.AddressDVO;
import com.github.handioq.fanshop.model.dvo.CategoryDVO;
import com.github.handioq.fanshop.model.dvo.ImageDVO;
import com.github.handioq.fanshop.model.dvo.OrderDVO;
import com.github.handioq.fanshop.model.dvo.OrderDetailsDVO;
import com.github.handioq.fanshop.model.dvo.ProductDVO;
import com.github.handioq.fanshop.model.dvo.ReviewDVO;
import com.github.handioq.fanshop.model.dvo.SizeDVO;
import com.github.handioq.fanshop.model.dvo.SpecificationDVO;
import com.github.handioq.fanshop.model.dvo.UserDVO;

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

    public static ProductDVO mapProductToDvo(ProductDTO productDTO) {
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

    public static List<ReviewDVO> mapReviewsToDvo(List<ReviewDTO> reviewsDTO) {
        List<ReviewDVO> reviewsDVO = new ArrayList<>(reviewsDTO.size());
        for (ReviewDTO reviewDTO : reviewsDTO) {
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
        return new CategoryDVO(categoryDTO.getId(), categoryDTO.getName(), categoryDTO.getImageUrl());
    }

    private static AddressDVO mapAddressToDvo(AddressDTO addressDTO) {
        return new AddressDVO(addressDTO.getStreet(), addressDTO.getCity(),
                addressDTO.getCountry(), addressDTO.getZipcode());
    }

    public static UserDVO mapUserToDvo(UserDTO userDTO) {
        return new UserDVO(userDTO.getId(), userDTO.getName(),
                userDTO.getAmountSpent(), userDTO.getEmail(),
                userDTO.getPhone(), mapAddressToDvo(userDTO.getAddress()));
    }

    private static OrderDVO mapOrderToDvo(OrderDTO orderDTO) {
        return new OrderDVO(orderDTO.getId(), orderDTO.getStatus(), mapProductsToDvo(orderDTO.getProducts()));
    }

    public static List<OrderDVO> mapOrdersToDvo(List<OrderDTO> ordersDTO) {
        List<OrderDVO> ordersDVO = new ArrayList<>(ordersDTO.size());
        for (OrderDTO orderDTO : ordersDTO) {
            ordersDVO.add(mapOrderToDvo(orderDTO));
        }
        return ordersDVO;
    }

    private static SizeDVO mapSizeToDvo(SizeDTO sizeDTO) {
        return new SizeDVO(sizeDTO.getSize());
    }

    private static List<SizeDVO> mapSizesToDvo(List<SizeDTO> sizesDTO) {
        List<SizeDVO> sizesDVO = new ArrayList<>(sizesDTO.size());
        for (SizeDTO sizeDTO : sizesDTO) {
            sizesDVO.add(mapSizeToDvo(sizeDTO));
        }
        return sizesDVO;
    }

    public static SpecificationDVO mapSpecificationToDvo(SpecificationDTO specificationDTO) {
        List<SizeDVO> sizesDVO = mapSizesToDvo(specificationDTO.getSizes());
        return new SpecificationDVO(specificationDTO.getColor(), specificationDTO.getCountry(),
                specificationDTO.getCode(), specificationDTO.getBrand(), sizesDVO);
    }

    public static OrderDetailsDVO mapOrdersDetailsToDvo(OrderDetailsDTO orderDetailsDTO) {
        return new OrderDetailsDVO(orderDetailsDTO.getId(), orderDetailsDTO.getStatus(),
                orderDetailsDTO.getDate(), mapProductsToDvo(orderDetailsDTO.getProducts()));
    }

}
