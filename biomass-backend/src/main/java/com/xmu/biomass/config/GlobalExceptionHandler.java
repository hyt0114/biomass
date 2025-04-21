package com.xmu.biomass.config;

import com.xmu.biomass.common.exception.BusinessException;
import com.xmu.biomass.common.exception.LoginFailException;
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
    public AjaxVo<Boolean> unauthorizedError(JwtException ex){
        AjaxVo<Boolean> ajaxVo = new AjaxVo<>();
        ajaxVo.setMessage("验证失败，请重新登录");
        ajaxVo.setData(true);
        return ajaxVo;
    }
    @ExceptionHandler(LoginFailException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public AjaxVo<?> loginError(LoginFailException ex){
        AjaxVo<?> ajaxVo = new AjaxVo<>();
        ajaxVo.setMessage(ex.getMessage());
        return ajaxVo;
    }
}
