package com.xmu.biomass.plant.vo;

import lombok.Data;

/**
 * @ Author：ythu
 * @ Date：2025/4/10  上午11:45
 */
@Data
public class CalculateResult {
    private Double above;
    private Double bellow;
    private Double total;
    private Double carbonRatioAbove;
    private Double carbonRatioBelow;
    private Double carbonRatioTotal;
}
