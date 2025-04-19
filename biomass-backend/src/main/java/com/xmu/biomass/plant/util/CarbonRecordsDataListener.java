package com.xmu.biomass.plant.util;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.read.listener.ReadListener;
import com.xmu.biomass.common.exception.BusinessException;
import com.xmu.biomass.plant.entity.Mangrove;
import com.xmu.biomass.plant.ro.CalcPlantRo;
import com.xmu.biomass.plant.ro.CalcSampleRo;
import com.xmu.biomass.plant.service.MangroveService;
import com.xmu.biomass.plant.vo.CarbonExcelVo;
import com.xmu.biomass.plant.vo.SingleExcelVoData;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @ Author：ythu
 * @ Date：2025/4/19  下午1:36
 */
@Data
public class CarbonRecordsDataListener implements ReadListener<CarbonExcelVo> {
    private final MangroveService mangroveService;
    private final SingleExcelVoData singleExcelVoData;
    public CarbonRecordsDataListener(MangroveService mangroveService,SingleExcelVoData singleExcelVoData){
        this.mangroveService = mangroveService;
        this.singleExcelVoData = singleExcelVoData;
    }
    private Map<Integer,CalcSampleRo> sampleMap = new HashMap<>();
    @Override
    public void invoke(CarbonExcelVo carbonExcelVo, AnalysisContext analysisContext) {
        Integer rowIndex = analysisContext.readRowHolder().getRowIndex();
        if(rowIndex.compareTo(2)>0){
            String sampleIndexStr = carbonExcelVo.getSampleIndex();
            if(!StringUtils.hasText(sampleIndexStr) || "......".equals(sampleIndexStr)){
                return;
            }
            Integer sampleIndex = Integer.valueOf(sampleIndexStr);
            CalcSampleRo calcSampleRo = sampleMap.get(sampleIndex);
            if(calcSampleRo==null){
                calcSampleRo = new CalcSampleRo();
                if(!StringUtils.hasText(carbonExcelVo.getSampleArea())){
                    throw new BusinessException("样方面积不能为空");
                }
                Double sampleArea = Double.valueOf(carbonExcelVo.getSampleArea());
                calcSampleRo.setSampleArea(sampleArea);
                List<CalcPlantRo> plants = new ArrayList<>();
                calcSampleRo.setPlants(plants);
                sampleMap.put(sampleIndex, calcSampleRo);
            }
            String mangroveName = carbonExcelVo.getMangroveName();
            if(StringUtils.hasText(mangroveName) && !mangroveName.equals("...")){
                mangroveName = mangroveName.trim();
                Mangrove mangrove = mangroveService.findExactByName(mangroveName);
                Objects.requireNonNull(mangrove,String.format("第%s行数据%s不在系统物种范围内，无法进行计算",rowIndex,mangroveName));
                CalcPlantRo plantRo = new CalcPlantRo();
                plantRo.setId(mangrove.getId());
                plantRo.setDbh(Double.valueOf(carbonExcelVo.getDbh()));
                plantRo.setHeight(Objects.isNull(carbonExcelVo.getHeight()) ? 0 :Double.parseDouble(carbonExcelVo.getHeight()));
                calcSampleRo.getPlants().add(plantRo);
            }
        }
    }

    @Override
    public void invokeHead(Map<Integer, ReadCellData<?>> headMap, AnalysisContext context) {
        BigDecimal monitorDateNum = headMap.get(1).getNumberValue();
        Objects.requireNonNull(monitorDateNum,"监测时间不能为空");
        LocalDate localDate = LocalDate.of(1900,1,1);
        localDate = localDate.plusDays(monitorDateNum.longValue()-2);
        singleExcelVoData.setMonitorDate(localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        BigDecimal monitorArea = headMap.get(3).getNumberValue();
        Objects.requireNonNull(monitorArea,"监测区域面积不能为空");
        singleExcelVoData.setMonitorArea(monitorArea.doubleValue());
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        singleExcelVoData.setSamples(sampleMap.values().stream().toList());
    }
}
