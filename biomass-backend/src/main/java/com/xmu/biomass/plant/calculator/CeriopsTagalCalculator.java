package com.xmu.biomass.plant.calculator;

import com.xmu.biomass.plant.ro.CalculatorRo;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @ Author：ythu
 * @ Date：2025/4/19  上午6:24
 * 角果木
 */
@Getter
@Component
public class CeriopsTagalCalculator extends CarbonRatioCalculator{

    private final Integer order = 4;

    private final String calcKey = "CeriopsTagal";

    public final String calcDescription = "适用于角果木";

    @Override
    public Double calculate(CalculatorRo ro) {
        if(Objects.isNull(ro.getHeight())){
            ro.setHeight(1D);
        }
        return this.calculateCarbon(ro.getRatio(),0.5199 * Math.pow(ro.getDbh(),1.953));
    }
}
