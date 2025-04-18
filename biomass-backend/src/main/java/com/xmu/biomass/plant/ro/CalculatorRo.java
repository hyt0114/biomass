package com.xmu.biomass.plant.ro;

import com.xmu.biomass.plant.enums.PlantCategoryEnum;
import lombok.Data;

/**
 * @ Author：ythu
 * @ Date：2025/4/10  上午9:53
 */
@Data
public class CalculatorRo {
    private double sampleArea;
    private double count;
    private double monitoringArea;
    private String monitoringDate;
    private String plant;
    private String category;
    private double dbh; // 胸径
    private double basal;// 基径
    private double height; // 树高
    private double ratio;
    private double density;
}
