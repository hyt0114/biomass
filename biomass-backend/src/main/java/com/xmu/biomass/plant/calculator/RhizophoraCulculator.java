package com.xmu.biomass.plant.calculator;

import com.xmu.biomass.plant.ro.CalculatorRo;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @ Author：ythu
 * @ Date：2025/4/19  上午6:32
 */
@Getter
@Component
public class RhizophoraCulculator extends CarbonRatioCalculator{

    private final Integer order = 6;

    private final String calcKey = "Rhizophora";

    public final String calcDescription = "适用于红树，拉式红树";

    @Override
    public Double calculate(CalculatorRo ro) {
        if(Objects.isNull(ro.getHeight())){
            ro.setHeight(1D);
        }
        double above = 0.235 * Math.pow(ro.getDbh(),2.42);
        double bellow = 0.00698 * Math.pow(ro.getDbh(),2.61);
        return this.calculateCarbon(ro.getRatio(), above, bellow);
    }
}
