package com.xmu.biomass.plant.controller;

import com.xmu.biomass.common.utils.ResponseUtil;
import com.xmu.biomass.common.vo.AjaxVo;
import com.xmu.biomass.plant.calculator.CarbonRatioCalculator;
import com.xmu.biomass.plant.entity.Mangrove;
import com.xmu.biomass.plant.ro.CalcFormsRo;
import com.xmu.biomass.plant.service.CalculatorService;
import com.xmu.biomass.plant.vo.CalcFormsResultVo;
import com.xmu.biomass.plant.vo.CalculateDiffResult;
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

    private final CalculatorService calculatorService;

    @PostMapping("/handle")
    public AjaxVo<CalculateDiffResult> doCalculate(@RequestBody CalcFormsRo ro) {
        return ResponseUtil.success(calculatorService.doCalculate(ro));
    }

}
