package com.xmu.biomass.plant.vo;

import com.xmu.biomass.common.vo.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ Author：ythu
 * @ Date：2025/4/6  上午9:15
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MangroveVo extends BaseVo {
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
    private String calcKey;
}
