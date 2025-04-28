package com.xmu.biomass.plant.calculator;

import com.xmu.biomass.plant.enums.PlantCategoryEnum;
import com.xmu.biomass.plant.ro.CalculatorRo;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @ Author：ythu
 * @ Date：2025/4/19  上午6:26
 */
@Getter
@Component
public class KandeliaCalculator extends CarbonRatioCalculator{

    private final Integer order = 5;

    private final String calcKey = "Kandelia";

    public final String calcDescription = "适用于秋茄";

    @Override
    public Double calculate(CalculatorRo ro) {
        if(Objects.isNull(ro.getHeight())){
            ro.setHeight(1D);
        }
        PlantCategoryEnum categoryEnum = processCategory(ro);
        if(categoryEnum == PlantCategoryEnum.ARBOR){
            return this.calculateArbor(ro);
        }
        return this.calculateYoung(ro);
    }

    private Double calculateYoung(CalculatorRo ro){
        // 幼龄
        double above = 0.01016 * Math.pow(ro.getDbh(),2.454);
        double bellow = 0.007649 * Math.pow(ro.getDbh(),2.064);
        return this.calculateCarbon(ro.getRatio(),above,bellow);
    }

    private Double calculateArbor(CalculatorRo ro){
        // 乔木
        double above = 0.03999 * Math.pow(Math.pow(ro.getDbh(),2) * ro.getHeight(),1.053);
        double bellow = 0.02972 * Math.pow(Math.pow(ro.getDbh(),2) * ro.getHeight(),0.990);
        return this.calculateCarbon(ro.getRatio(),above,bellow);
    }

    private PlantCategoryEnum processCategory(CalculatorRo ro){
        if(ro.getHeight().compareTo(2D) < 0){
            return PlantCategoryEnum.YOUNG;
        }
        return PlantCategoryEnum.ARBOR;
    }
}
