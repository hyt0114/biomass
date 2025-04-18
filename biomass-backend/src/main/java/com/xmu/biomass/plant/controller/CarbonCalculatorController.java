package com.xmu.biomass.plant.controller;

import com.xmu.biomass.common.utils.ResponseUtil;
import com.xmu.biomass.common.vo.AjaxVo;
import com.xmu.biomass.plant.calculator.CarbonRatioCalculator;
import com.xmu.biomass.plant.entity.Mangrove;
import com.xmu.biomass.plant.ro.CalcFormsRo;
import com.xmu.biomass.plant.vo.CalcFormsResultVo;
import com.xmu.biomass.plant.vo.CalculateResult;
import com.xmu.biomass.plant.service.MangroveService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

/**
 * @ Author：ythu
 * @ Date：2025/4/10  上午11:39
 */
@RestController
@RequestMapping("/carbon/calc")
@RequiredArgsConstructor
public class CarbonCalculatorController {

    private final List<CarbonRatioCalculator> calculators;
    private final MangroveService mangroveService;

    @PostMapping("/handle")
    public AjaxVo<CalcFormsResultVo> doCalculate(@RequestBody CalcFormsRo ro) {
        Mangrove mangroveOne = mangroveService.selectById(ro.getFormOne().getPlant());
        CarbonRatioCalculator calculatorOne = getCalculator(mangroveOne.getCalcKey());
        Objects.requireNonNull(calculatorOne,"没有该类型的植株计算公式");
        ro.getFormOne().setRatio(mangroveOne.getCarbonContentRatio());
        ro.getFormOne().setDensity(mangroveOne.getDensity());
        CalculateResult resultOne = calculatorOne.calculate(ro.getFormOne());
        resultOne.setMonitoringDate(ro.getFormOne().getMonitoringDate());

        Mangrove mangroveTwo = mangroveService.selectById(ro.getFormTwo().getPlant());
        CarbonRatioCalculator calculatorTwo = getCalculator(mangroveTwo.getCalcKey());
        Objects.requireNonNull(calculatorTwo,"没有该类型的植株计算公式");
        ro.getFormTwo().setRatio(mangroveTwo.getCarbonContentRatio());
        ro.getFormTwo().setDensity(mangroveTwo.getDensity());
        CalculateResult resultTwo = calculatorTwo.calculate(ro.getFormTwo());
        resultTwo.setMonitoringDate(ro.getFormTwo().getMonitoringDate());

        CalcFormsResultVo resultVo = new CalcFormsResultVo();
        resultVo.setResultOne(resultOne);
        resultVo.setResultTwo(resultTwo);

        double increment = Double.parseDouble(resultTwo.getCarbonStorage()) - Double.parseDouble(resultOne.getCarbonStorage());

        resultVo.setCarbonStorageIncrement(String.valueOf(increment));
        return ResponseUtil.success(resultVo);
    }
    private CarbonRatioCalculator getCalculator(String calcKey) {
        return calculators.stream().filter(c->c.getCalcKey().equals(calcKey)).findFirst().orElse(null);
    }
}
