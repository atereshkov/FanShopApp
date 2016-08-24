package com.github.handioq.fanshop.util;

import com.github.handioq.fanshop.model.dto.SizeDTO;

import java.util.List;

public class StringUtils {

    private static final String SIZES_DELIMITER = " ";

    public static String getStringSizes(List<SizeDTO> sizes) {
        StringBuilder sb = new StringBuilder();

        for (SizeDTO size : sizes) {
            sb.append(size.getSize()).append(SIZES_DELIMITER);
        }

        return sb.toString();
    }

}
