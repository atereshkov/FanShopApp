package com.github.handioq.fanshop.util;

import com.github.handioq.fanshop.model.dvo.SizeDVO;

import java.util.List;

public class StringUtils {

    private static final String SIZES_DELIMITER = " ";

    public static String getStringSizes(List<SizeDVO> sizes) {
        StringBuilder sb = new StringBuilder();

        for (SizeDVO size : sizes) {
            sb.append(size.getSize()).append(SIZES_DELIMITER);
        }

        return sb.toString();
    }

}
