package com.xmu.biomass.plant.ro;

import lombok.Data;

import java.util.List;

/**
 * @ Author：ythu
 * @ Date：2025/4/18  上午11:02
 */
@Data
public class CalcSampleRo {
    private Double sampleArea;
    private List<CalcPlantRo> plants;
}
