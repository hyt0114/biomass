package com.xmu.biomass.plant.vo;

import lombok.Data;

import java.util.List;

/**
 * @ Author：ythu
 * @ Date：2025/4/18  上午11:27
 */
@Data
public class SampleResult {
    // 样方面积
    private Double sampleArea;
    // 样本数量
    private Integer count;
    // 植株类型
    private List<String> types;
    // 样方碳储量
    private Double storage;
    // 单位面积碳储量
    private Double averageStorage;
}
