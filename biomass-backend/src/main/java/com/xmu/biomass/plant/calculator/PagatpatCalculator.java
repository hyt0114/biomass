package com.xmu.biomass.plant.calculator;

import com.xmu.biomass.plant.ro.CalculatorRo;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @ Author：ythu
 * @ Date：2025/4/19  上午6:48
 */
@Getter
@Component
public class PagatpatCalculator extends CarbonRatioCalculator{

    private final Integer order = 10;

    private final String calcKey = "Pagatpat";

    public final String calcDescription = "适用于无瓣海桑";

    @Override
    public Double calculate(CalculatorRo ro) {
        if(Objects.isNull(ro.getHeight())){
            ro.setHeight(1D);
        }
        return this.calculateCarbon(ro.getRatio(),6.5088 * Math.pow(Math.pow(ro.getDbh(),2) * ro.getHeight(),0.38));
    }
}
