package com.xmu.biomass.common.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Base64;

/**
 * @ Author：ythu
 * @ Date：2025/4/21  下午3:43
 */
@Component
@RequiredArgsConstructor
public class PasswordUtil {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public String encode(String password) {
        return bCryptPasswordEncoder.encode(password);
    }
    public String encode(String password,Boolean isBase64) {
        if(isBase64){
            password = this.decodeBase64(password);
        }
        return this.encode(password);
    }
    public String decodeBase64(String password) {
        return new String(Base64.getDecoder().decode(password));
    }
    public boolean matches(String rawPassword, String encodedPassword) {
        return bCryptPasswordEncoder.matches(rawPassword, encodedPassword);
    }
    public boolean matches(String rawPassword, String encodedPassword,Boolean isBase64) {
        if(isBase64){
            rawPassword = this.decodeBase64(rawPassword);
        }
        return this.matches(rawPassword, encodedPassword);
    }
}
