package com.xmu.biomass.plant.calculator;

import com.xmu.biomass.plant.ro.CalculatorRo;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @ Author：ythu
 * @ Date：2025/4/18  下午9:56
 * 通用方程
 */
@Getter
@Component
public class CommonCalculator extends CarbonRatioCalculator{

    private final Integer order = 999;

    private final String calcKey = "Common";

    public final String calcDescription = "通用方程";

    @Override
    public Double calculate(CalculatorRo ro) {
        if(Objects.isNull(ro.getHeight())){
            ro.setHeight(1D);
        }
        double above = 0.251 * ro.getDensity() * Math.pow(ro.getDbh(),2.46);
        double bellow = 0.199 * Math.pow(ro.getDensity(),0.899) * Math.pow(ro.getDbh(),2.22);
        return this.calculateCarbon(ro.getRatio(),above,bellow);
    }
}
