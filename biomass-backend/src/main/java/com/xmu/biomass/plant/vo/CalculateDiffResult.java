package com.xmu.biomass.plant.vo;

import lombok.Data;

import java.util.List;

/**
 * @ Author：ythu
 * @ Date：2025/4/18  下午8:34
 */
@Data
public class CalculateDiffResult {
    private List<SampleResult> firstSampleResults;
    private List<SampleResult> secondSampleResults;
    private Double firstTotal;
    private Double secondTotal;
    private Double difference;
    private String firstMonitorDate;
    private String secondMonitorDate;
}
