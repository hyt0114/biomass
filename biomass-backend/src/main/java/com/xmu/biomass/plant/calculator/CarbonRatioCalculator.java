package com.xmu.biomass.plant.calculator;

import com.xmu.biomass.plant.vo.CalculateResult;
import com.xmu.biomass.plant.ro.CalculatorRo;

import java.text.DecimalFormat;
import java.util.Arrays;

/**
 * @ Author：ythu
 * @ Date：2025/4/9  下午4:13
 */
public abstract class CarbonRatioCalculator {
    public abstract String getCalcKey();
    abstract DecimalFormat formatter();
    public abstract CalculateResult calculate(CalculatorRo ro);
    protected double calculateCarbon(double ratio,double... carbons) {
        return Arrays.stream(carbons).sum() * ratio;
    }
}
