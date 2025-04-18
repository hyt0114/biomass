package com.xmu.biomass.plant.ro;

import com.xmu.biomass.plant.enums.PlantCategoryEnum;
import lombok.Data;

/**
 * @ Author：ythu
 * @ Date：2025/4/10  上午9:53
 */
@Data
public class CalculatorRo {
    private Double dbh; // 胸径
    private Double height; // 树高
    private Double ratio;
    private Double density;
}
