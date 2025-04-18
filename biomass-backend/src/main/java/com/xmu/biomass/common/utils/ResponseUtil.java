package com.xmu.biomass.common.utils;

import com.xmu.biomass.common.vo.AjaxVo;

/**
 * @ Author：ythu
 * @ Date：2025/3/21  下午4:30
 */
public class ResponseUtil<T> {
    public static AjaxVo<?> success(){
        return new AjaxVo<>();
    }
    public static <T> AjaxVo<T> success(T data){
        AjaxVo<T> ajaxVo = new AjaxVo<>();
        ajaxVo.setData(data);
        return ajaxVo;
    }
}
