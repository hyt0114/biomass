package com.xmu.biomass.plant.calculator;

import com.xmu.biomass.plant.ro.CalculatorRo;
import com.xmu.biomass.plant.vo.CalculateResult;

import java.text.DecimalFormat;
import java.util.Arrays;

/**
 * @ Author：ythu
 * @ Date：2025/4/9  下午4:13
 */
public abstract class CarbonRatioCalculator {
    public abstract Integer getOrder();
    public abstract String getCalcKey();
    public abstract String getCalcDescription();
    public abstract Double calculate(CalculatorRo ro);
    protected Double calculateCarbon(double ratio,double... carbons) {
        return Arrays.stream(carbons).sum() * ratio;
    }
}
