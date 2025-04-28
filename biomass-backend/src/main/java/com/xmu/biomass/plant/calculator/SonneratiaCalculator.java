package com.xmu.biomass.plant.calculator;

import com.xmu.biomass.plant.ro.CalculatorRo;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @ Author：ythu
 * @ Date：2025/4/19  上午6:44
 */
@Getter
@Component
public class SonneratiaCalculator extends CarbonRatioCalculator{

    private final Integer order = 8;

    private final String calcKey = "Sonneratia";

    public final String calcDescription = "适用于杯萼海桑、海桑、海南海桑、卵叶海桑、拟海桑";

    @Override
    public Double calculate(CalculatorRo ro) {
        if(Objects.isNull(ro.getHeight())){
            ro.setHeight(1D);
        }
        return this.calculateCarbon(ro.getRatio(),0.08469 * Math.pow(Math.pow(ro.getDbh(),2) * ro.getHeight(),0.8532));
    }
}
