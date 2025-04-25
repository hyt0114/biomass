package com.xmu.biomass.common.utils;

import org.springframework.util.StringUtils;

import java.util.Base64;
import java.util.Objects;

/**
 * @ Author：ythu
 * @ Date：2025/4/25  上午10:44
 */
public class ImageToBase64 {
    public static String convert(byte[] imageBytes,String fileName) {
        String mimeType = "";
        if(!Objects.isNull(fileName)){
            mimeType = getMimeType(fileName);
        }
        return (StringUtils.hasText(mimeType) ? ("data:" + mimeType + ";base64,"):"") + Base64.getEncoder().encodeToString(imageBytes);
    }
    private static String getMimeType(String fileName) {
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        return switch (extension) {
            case "png" -> "image/png";
            case "jpg", "jpeg" -> "image/jpeg";
            case "gif" -> "image/gif";
            case "bmp" -> "image/bmp";
            default -> throw new IllegalArgumentException("Unsupported image type: " + extension);
        };
    }
}
