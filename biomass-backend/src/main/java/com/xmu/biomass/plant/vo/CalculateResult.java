package com.xmu.biomass.plant.vo;

import lombok.Data;

/**
 * @ Author：ythu
 * @ Date：2025/4/10  上午11:45
 */
@Data
public class CalculateResult {
    private String monitoringDate;
    private String above;
    private String bellow;
    private String total;
    private String carbonRatioAbove;
    private String carbonRatioBelow;
    private String carbonRatioTotal;
    private String carbonStorage;
}
