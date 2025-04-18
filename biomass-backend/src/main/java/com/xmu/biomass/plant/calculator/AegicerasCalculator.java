package com.xmu.biomass.plant.calculator;

import com.xmu.biomass.plant.enums.PlantCategoryEnum;
import com.xmu.biomass.plant.vo.CalculateResult;
import com.xmu.biomass.plant.ro.CalculatorRo;
import com.xmu.biomass.plant.util.DecimalFormatter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;

/**
 * @ Author：ythu
 * @ Date：2025/4/9  下午5:30
 * 桐花树
 */
@Component
@RequiredArgsConstructor
public class AegicerasCalculator extends CarbonRatioCalculator{

    private final DecimalFormatter formatter;

    @Getter
    private final String calcKey = "Aegiceras";

    @Override
    public DecimalFormat formatter() {
        return formatter.getFormatter();
    }

    @Override
    public CalculateResult calculate(CalculatorRo ro) {
        if(PlantCategoryEnum.ARBOR.getCode().equals(ro.getCategory())){
           return this.calculateArbor(ro);
        }else if(PlantCategoryEnum.SMALL_ARBOR.getCode().equals(ro.getCategory())){
            return this.calculateSmallArbor(ro);
        }else if(PlantCategoryEnum.BUSH.getCode().equals(ro.getCategory())){
            return this.calculateBush(ro);
        }
        return null;
    }
    public CalculateResult calculateArbor(CalculatorRo ro) {

        DecimalFormat df = this.formatter();
        double above = Math.pow(10,0.465 * Math.log10(Math.pow(ro.getDbh()/10,2) * ro.getHeight()) + 1.496);
        double bellow = Math.pow(10,0.303 * Math.log10(Math.pow(ro.getDbh()/10,2) * ro.getHeight()) + 0.967);
        double total = above + bellow;
        CalculateResult result = new CalculateResult();
        result.setAbove(df.format(above));
        result.setBellow(df.format(bellow));
        result.setTotal(df.format(total));
        double carbonRatioAbove = this.calculateCarbon(ro.getRatio(),above);
        result.setCarbonRatioAbove(df.format(carbonRatioAbove));
        double carbonRatioBellow = this.calculateCarbon(ro.getRatio(),bellow);
        result.setCarbonRatioBelow(df.format(carbonRatioBellow));
        double carbonRatioTotal = this.calculateCarbon(ro.getRatio(),total);
        result.setCarbonRatioTotal(df.format(carbonRatioTotal));
        double carbonStorage = carbonRatioTotal * ro.getCount() * ro.getMonitoringArea() / ro.getSampleArea();
        result.setCarbonStorage(df.format(carbonStorage));
        return result;
    }

    public CalculateResult calculateSmallArbor(CalculatorRo ro) {

        DecimalFormat df = this.formatter();
        double above = 0.644347 * ro.getDbh() - 0.425066;
        double bellow = 0.163242 * ro.getDbh() - 0.10423;
        double total = above + bellow;
        CalculateResult result = new CalculateResult();
        result.setAbove(df.format(above));
        result.setBellow(df.format(bellow));
        result.setTotal(df.format(total));
        double carbonRatioAbove = this.calculateCarbon(ro.getRatio(),above);
        result.setCarbonRatioAbove(df.format(carbonRatioAbove));
        double carbonRatioBellow = this.calculateCarbon(ro.getRatio(),bellow);
        result.setCarbonRatioBelow(df.format(carbonRatioBellow));
        double carbonRatioTotal = this.calculateCarbon(ro.getRatio(),total);
        result.setCarbonRatioTotal(df.format(carbonRatioTotal));
        double carbonStorage = carbonRatioTotal * ro.getCount() * ro.getMonitoringArea() / ro.getSampleArea();
        result.setCarbonStorage(df.format(carbonStorage));
        return result;
    }

    public CalculateResult calculateBush(CalculatorRo ro) {

        DecimalFormat df = this.formatter();
        double above = 0.644347 * ro.getDbh() - 0.425066;
        double bellow = 0.163242 * ro.getDbh() - 0.10423;
        double total = above + bellow;
        CalculateResult result = new CalculateResult();
        result.setAbove(df.format(above));
        result.setBellow(df.format(bellow));
        result.setTotal(df.format(total));
        double carbonRatioAbove = this.calculateCarbon(ro.getRatio(),above);
        result.setCarbonRatioAbove(df.format(carbonRatioAbove));
        double carbonRatioBellow = this.calculateCarbon(ro.getRatio(),bellow);
        result.setCarbonRatioBelow(df.format(carbonRatioBellow));
        double carbonRatioTotal = this.calculateCarbon(ro.getRatio(),total);
        result.setCarbonRatioTotal(df.format(carbonRatioTotal));
        double carbonStorage = carbonRatioTotal * ro.getCount() * ro.getMonitoringArea() / ro.getSampleArea();
        result.setCarbonStorage(df.format(carbonStorage));
        return result;
    }

}
