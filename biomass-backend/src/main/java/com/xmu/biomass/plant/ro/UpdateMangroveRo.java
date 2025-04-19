package com.xmu.biomass.plant.ro;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ Author：ythu
 * @ Date：2025/4/6  上午7:50
 */
@Data
public class UpdateMangroveRo {
    private Integer id;
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
    private MultipartFile img;
    private String formula;
}
