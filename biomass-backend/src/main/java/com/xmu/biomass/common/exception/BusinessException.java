package com.xmu.biomass.common.exception;

/**
 * @ Author：ythu
 * @ Date：2025/3/21  下午4:42
 */
public class BusinessException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public BusinessException() {}
    public BusinessException(String message) {
        super(message);
    }
}
