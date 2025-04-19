package com.xmu.biomass.plant.calculator;

import com.xmu.biomass.plant.ro.CalculatorRo;
import lombok.Getter;
import org.springframework.stereotype.Component;

/**
 * @ Author：ythu
 * @ Date：2025/4/18  下午10:04
 * 木榄、海莲，尖瓣海莲
 */
@Getter
@Component
public class BgymnorrhizaCalculator extends CarbonRatioCalculator{

    private final Integer order = 3;

    private final String calcKey = "Bgymnorrhiza";

    public final String calcDescription = "适用于木榄、海莲、尖瓣海莲";

    @Override
    public Double calculate(CalculatorRo ro) {
        double above = 0.186 * Math.pow(ro.getDbh(),2.31);
        double bellow = 0.4699 * Math.pow((Math.pow(ro.getDbh(),2) * ro.getHeight()),1.5441);
        return this.calculateCarbon(ro.getRatio(), above, bellow);
    }
}
