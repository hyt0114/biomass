package com.xmu.biomass.plant.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @ Author：ythu
 * @ Date：2025/4/19  下午1:37
 */
@Data
public class CarbonExcelVo {
    @ExcelProperty(index=0)
    private String sampleIndex;
    @ExcelProperty(index=1)
    private String sampleArea;
    @ExcelProperty(index=2)
    private String mangroveName;
    @ExcelProperty(index=3)
    private String crown;
    @ExcelProperty(index=4)
    private String height;
    @ExcelProperty(index=5)
    private String dbh;
}
