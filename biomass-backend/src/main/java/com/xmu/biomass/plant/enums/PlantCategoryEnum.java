package com.xmu.biomass.plant.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @ Author：ythu
 * @ Date：2025/4/9  下午5:42
 */
public enum PlantCategoryEnum {

    ARBOR("1","乔木"),
    SMALL_ARBOR("2","小乔木"),
    SHRUB("3","灌木"),
    BUSH("4","矮灌木"),
    YOUNG("5","幼龄植株");



    @EnumValue
    private String code;
    private String description;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private PlantCategoryEnum(String code, String description) {
        this.code= code;
        this.description = description;
    }
}
