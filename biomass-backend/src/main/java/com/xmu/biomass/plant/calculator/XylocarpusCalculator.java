package com.xmu.biomass.plant.calculator;

import com.xmu.biomass.plant.ro.CalculatorRo;
import lombok.Getter;
import org.springframework.stereotype.Component;

/**
 * @ Author：ythu
 * @ Date：2025/4/19  上午6:50
 * 木果楝
 */
@Getter
@Component
public class XylocarpusCalculator extends CarbonRatioCalculator{

    private final Integer order = 9;

    private final String calcKey = "Xylocarpus";

    private final String calcDescription = "适用于木果楝";

    @Override
    public Double calculate(CalculatorRo ro) {
        double above = 0.0823 * Math.pow(ro.getDbh(), 2.59);
        double below = 0.145 * Math.pow(ro.getDbh(), 2.55);
        return this.calculateCarbon(ro.getRatio(), above, below);
    }
}
