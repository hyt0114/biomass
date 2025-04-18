package com.xmu.biomass.plant.calculator;

import com.xmu.biomass.plant.enums.PlantCategoryEnum;
import com.xmu.biomass.plant.ro.CalculatorRo;
import lombok.Getter;
import org.springframework.stereotype.Component;

/**
 * @ Author：ythu
 * @ Date：2025/4/18  下午9:49
 * 白骨壤
 */
@Getter
@Component
public class AvicenniaCalculator extends CarbonRatioCalculator{
    private final String calcKey = "Avicennia";

    public final String calcDescription = "适用于白骨壤";

    @Override
    public Double calculate(CalculatorRo ro) {
        PlantCategoryEnum categoryEnum = processCategory(ro);
        if (categoryEnum == PlantCategoryEnum.SHRUB){
            return calculateShrub(ro);
        }
        return calculateArbor(ro);
    }

    private Double calculateShrub(CalculatorRo ro){
        // 灌木
        return this.calculateCarbon(ro.getRatio(),0.116291 * ro.getDbh() * ro.getHeight() - 0.348657);
    }
    private Double calculateArbor(CalculatorRo ro){
        // 乔木 1.5066 · DBH 1.595
        return this.calculateCarbon(ro.getRatio(),1.5066 * Math.pow(ro.getDbh(),1.595));
    }


    private PlantCategoryEnum processCategory(CalculatorRo ro){
        if(ro.getDbh().compareTo(3.7) >= 0
            && ro.getDbh().compareTo(4.6) <= 0
            && ro.getHeight().compareTo(2D) < 0){
            return PlantCategoryEnum.SHRUB;
        }
        return PlantCategoryEnum.ARBOR;
    }
}
