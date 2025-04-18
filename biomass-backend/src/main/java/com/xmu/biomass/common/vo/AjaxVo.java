package com.xmu.biomass.common.vo;

import lombok.Data;

/**
 * @ Author：ythu
 * @ Date：2025/3/21  下午4:27
 */
@Data
public class AjaxVo<T> {
    private boolean success = true;
    private String message;
    private T data;
}
