package com.xmu.biomass.plant.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;

/**
 * @ Author：ythu
 * @ Date：2025/4/10  上午11:12
 */
@ConfigurationProperties(prefix="calc")
@Data
@Component
public class DecimalFormatter {
    private int precision;

    public DecimalFormat getFormatter(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < precision; i++) {
            sb.append("0");
        }
        return new DecimalFormat("#."+sb.toString());
    }
}
