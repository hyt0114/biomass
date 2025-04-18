package com.xmu.biomass.plant.ro;

import lombok.Data;

import java.util.List;

/**
 * @ Author：ythu
 * @ Date：2025/4/16  下午11:13
 */
@Data
public class CalcFormsRo {
    private String firstMonitorDate;
    private Double firstMonitorArea;
    private List<CalcSampleRo> firstSamples;
    private String secondMonitorDate;
    private Double secondMonitorArea;
    private List<CalcSampleRo> secondSamples;
}
