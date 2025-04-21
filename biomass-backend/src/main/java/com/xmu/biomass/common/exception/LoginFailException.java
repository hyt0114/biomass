package com.xmu.biomass.common.exception;

/**
 * @ Author：ythu
 * @ Date：2025/4/21  下午3:51
 */
public class LoginFailException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public LoginFailException() {}
    public LoginFailException(String message) {
        super(message);
    }
}
