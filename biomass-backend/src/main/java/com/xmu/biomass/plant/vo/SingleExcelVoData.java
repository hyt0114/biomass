package com.xmu.biomass.plant.vo;

import com.xmu.biomass.plant.ro.CalcSampleRo;
import lombok.Data;

import java.util.List;

/**
 * @ Author：ythu
 * @ Date：2025/4/19  下午1:44
 */
@Data
public class SingleExcelVoData {
    private String monitorDate;
    private Double monitorArea;
    private List<CalcSampleRo> samples;
}
