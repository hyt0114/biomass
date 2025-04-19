package com.xmu.biomass.plant.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.xmu.biomass.plant.calculator.CarbonRatioCalculator;
import com.xmu.biomass.plant.entity.Mangrove;
import com.xmu.biomass.plant.ro.CalcFormsRo;
import com.xmu.biomass.plant.ro.CalcPlantRo;
import com.xmu.biomass.plant.ro.CalcSampleRo;
import com.xmu.biomass.plant.ro.CalculatorRo;
import com.xmu.biomass.plant.util.CarbonRecordsDataListener;
import com.xmu.biomass.plant.vo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @ Author：ythu
 * @ Date：2025/4/18  上午11:18
 */
@Service
@RequiredArgsConstructor
public class CalculatorService {
    private final List<CarbonRatioCalculator> calculators;
    private final MangroveService mangroveService;


    public CalculateDiffResult doCalculate(CalcFormsRo ro) {
        List<SampleResult> firstSampleResultList = calcSampleResults(ro.getFirstSamples());
        List<SampleResult> secondSampleResultList = calcSampleResults(ro.getSecondSamples());
        Double firstTotal = firstSampleResultList.stream().mapToDouble(SampleResult::getAverageStorage).average().orElse(0D) * ro.getFirstMonitorArea();
        Double secondTotal = secondSampleResultList.stream().mapToDouble(SampleResult::getAverageStorage).average().orElse(0D) * ro.getSecondMonitorArea();
        CalculateDiffResult result = new CalculateDiffResult();
        result.setFirstTotal(firstTotal);
        result.setSecondTotal(secondTotal);
        result.setFirstSampleResults(firstSampleResultList);
        result.setSecondSampleResults(secondSampleResultList);
        result.setFirstMonitorDate(ro.getFirstMonitorDate());
        result.setSecondMonitorDate(ro.getSecondMonitorDate());
        result.setDifference(secondTotal- firstTotal);
        return result;
    }
    private List<SampleResult> calcSampleResults(List<CalcSampleRo> list) {
        return list.stream().map(sample -> {
            SampleResult sampleResult = new SampleResult();
            sampleResult.setSampleArea(sample.getSampleArea());
            if(CollectionUtils.isEmpty(sample.getPlants())){
                sampleResult.setCount(0);
                sampleResult.setStorage(0D);
            }else{
                sampleResult.setCount(sample.getPlants().size());
                List<Integer> ids = sample.getPlants().stream().map(CalcPlantRo::getId).toList();
                List<Mangrove> sampleMangroveList = mangroveService.selectByIds(ids);
                sampleResult.setTypes(sampleMangroveList.stream().map(Mangrove::getName).toList());
                Map<Integer,Mangrove> sampleMangroveMap =   sampleMangroveList.stream().collect(Collectors.toMap(Mangrove::getId, Function.identity()));
                Double sampleStorage = sample.getPlants().stream().map(plant->{
                    Mangrove mangrove  = sampleMangroveMap.get(plant.getId());
                    CarbonRatioCalculator calculator = getCalculator(mangrove.getFormula());
                    CalculatorRo calcParam = new CalculatorRo();
                    calcParam.setDbh(plant.getDbh());
                    calcParam.setHeight(plant.getHeight());
                    calcParam.setDensity(mangrove.getDensity());
                    calcParam.setRatio(mangrove.getCarbonContentRatio());
                    return calculator.calculate(calcParam);
                }).mapToDouble(Double::valueOf).sum();
                sampleResult.setStorage(sampleStorage);
                sampleResult.setAverageStorage(sampleStorage/sample.getSampleArea());
            }
            return sampleResult;
        }).toList();
    }

    /**
     * 根据key获取计算器
     * @param calcKey
     * @return
     */
    private CarbonRatioCalculator getCalculator(String calcKey) {
        return calculators.stream().filter(c->c.getCalcKey().equals(calcKey)).findFirst().orElse(null);
    }

    public CalcFormsExcelVo parseExcel(MultipartFile file) {
        SingleExcelVoData firstExcelVoData = new SingleExcelVoData();
        SingleExcelVoData secondExcelVoData = new SingleExcelVoData();
        try (ExcelReader excelReader = EasyExcel.read(file.getInputStream()).build()) {
            ReadSheet readSheet1 =
                    EasyExcel.readSheet(0).head(CarbonExcelVo.class).registerReadListener(new CarbonRecordsDataListener(mangroveService,firstExcelVoData)).build();
            ReadSheet readSheet2 =
                    EasyExcel.readSheet(1).head(CarbonExcelVo.class).registerReadListener(new CarbonRecordsDataListener(mangroveService,secondExcelVoData)).build();
            excelReader.read(readSheet1, readSheet2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        CalcFormsExcelVo vo = new CalcFormsExcelVo();
        vo.setFirstMonitorArea(firstExcelVoData.getMonitorArea());
        vo.setFirstMonitorDate(firstExcelVoData.getMonitorDate());
        vo.setFirstSamples(firstExcelVoData.getSamples());
        vo.setSecondMonitorArea(secondExcelVoData.getMonitorArea());
        vo.setSecondMonitorDate(secondExcelVoData.getMonitorDate());
        vo.setSecondSamples(secondExcelVoData.getSamples());
        return vo;
    }
}
