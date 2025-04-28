package com.xmu.biomass.plant.calculator;

import com.xmu.biomass.plant.ro.CalculatorRo;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @ Author：ythu
 * @ Date：2025/4/19  上午6:53
 */
@Getter
@Component
public class LagunculariaCalculator extends CarbonRatioCalculator{

    private final Integer order = 11;

    private final String calcKey = "Laguncularia";

    public final String calcDescription = "适用于拉关木";

    @Override
    public Double calculate(CalculatorRo ro) {
        if(Objects.isNull(ro.getHeight())){
            ro.setHeight(1D);
        }
        double above = 0.102 * Math.pow(ro.getDbh(),2.50);
        double below = 3.622 * Math.pow(ro.getDbh(),1.930);
        return this.calculateCarbon(ro.getRatio(),above,below);
    }
}
