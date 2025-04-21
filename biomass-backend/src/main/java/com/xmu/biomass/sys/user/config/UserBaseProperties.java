package com.xmu.biomass.sys.user.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ Author：ythu
 * @ Date：2025/4/21  下午4:42
 */
@ConfigurationProperties(prefix = "user.default")
@Component
@Data
public class UserBaseProperties {
    private String password;
}
