package com.xmu.biomass.plant.calculator;

import com.xmu.biomass.plant.enums.PlantCategoryEnum;
import com.xmu.biomass.plant.ro.CalculatorRo;
import com.xmu.biomass.plant.vo.CalculateResult;
import com.xmu.biomass.plant.util.DecimalFormatter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.Objects;

/**
 * @ Author：ythu
 * @ Date：2025/4/9  下午5:30
 * 桐花树
 */
@Component
@Getter
public class AegicerasCalculator extends CarbonRatioCalculator{

    private final Integer order = 1;

    private final String calcKey = "Aegiceras";

    private final String calcDescription = "适用于桐花树";

    @Override
    public Double calculate(CalculatorRo ro) {
        if(Objects.isNull(ro.getHeight())){
            ro.setHeight(1D);
        }
        PlantCategoryEnum categoryEnum = processCategory(ro);
        if(PlantCategoryEnum.BUSH == categoryEnum){
            return this.calculateBush(ro);
        }
        return this.calcuateShrub(ro);
    }
    private PlantCategoryEnum processCategory(CalculatorRo ro){
        if(ro.getHeight().compareTo(2D) < 0){
            return PlantCategoryEnum.BUSH;
        }
        return PlantCategoryEnum.SHRUB;
    }

    private Double calcuateShrub(CalculatorRo ro){
        return this.calculateCarbon(ro.getRatio(),0.780778 * ro.getDbh() - 0.325215);
    }

    private Double calculateBush(CalculatorRo ro) {
        // 使用基径 dbh=d0
        double above =  0.02039 * Math.pow(Math.pow(ro.getDbh(),2) * ro.getHeight(),0.83749);
        double bellow = above / 7;
        return this.calculateCarbon(ro.getRatio(),above , bellow);
    }

}
