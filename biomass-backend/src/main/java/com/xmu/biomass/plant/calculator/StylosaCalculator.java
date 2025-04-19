package com.xmu.biomass.plant.calculator;

import com.xmu.biomass.plant.ro.CalculatorRo;
import lombok.Getter;
import org.springframework.stereotype.Component;

/**
 * @ Author：ythu
 * @ Date：2025/4/19  上午6:37
 * 红海榄
 */
@Getter
@Component
public class StylosaCalculator extends CarbonRatioCalculator{

    private final Integer order = 7;

    private final String calcKey = "Stylosa";

    public final String calcDescription = "适用于红海榄";

    @Override
    public Double calculate(CalculatorRo ro) {
        return this.calculateCarbon(ro.getRatio(), 0.1719 * Math.pow(Math.pow(ro.getDbh(),2) * ro.getHeight(),1.0254));
    }
}
