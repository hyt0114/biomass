package com.xmu.biomass.config;

import com.xmu.biomass.common.exception.BusinessException;
import com.xmu.biomass.common.vo.AjaxVo;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ Author：ythu
 * @ Date：2025/3/21  下午4:37
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public AjaxVo<?>  npeError(NullPointerException ex){
        AjaxVo<?> ajaxVo = new AjaxVo<>();
        ajaxVo.setMessage(ex.getMessage());
        return ajaxVo;
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public AjaxVo<?> businessError(BusinessException ex){
        AjaxVo<?> ajaxVo = new AjaxVo<>();
        ajaxVo.setMessage(ex.getMessage());
        return ajaxVo;
    }
    @ExceptionHandler(JwtException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public AjaxVo<?> unauthorizedError(JwtException ex){
        AjaxVo<?> ajaxVo = new AjaxVo<>();
        ajaxVo.setMessage("登录已过期，请重新登录");
        return ajaxVo;
    }

}
