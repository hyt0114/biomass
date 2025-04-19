package com.xmu.biomass.plant.entity;

import com.xmu.biomass.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ Author：ythu
 * @ Date：2025/4/6  上午7:14
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Mangrove extends BaseEntity {
    // 中文名称
    private String name;
    // 学名
    private String scientificName;
    // 描述
    private String description;
    // 密度
    private Double density;
    // 含碳率
    private Double carbonContentRatio;
    // 图片
    private String img;
    private String formula;
}
