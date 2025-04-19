package com.xmu.biomass.plant.controller;

import com.xmu.biomass.common.utils.ResponseUtil;
import com.xmu.biomass.common.vo.AjaxVo;
import com.xmu.biomass.plant.ro.CalcFormsRo;
import com.xmu.biomass.plant.service.CalculatorService;
import com.xmu.biomass.plant.vo.CalcFormsExcelVo;
import com.xmu.biomass.plant.vo.CalculateDiffResult;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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
    @PostMapping("/download-template")
    public void downloadTemplate(HttpServletResponse response) {
        ClassPathResource classPathResource = new ClassPathResource("templates/record-template.xlsx");
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=record-template.xlsx");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");

        try(InputStream inputStream = classPathResource.getInputStream();OutputStream outputStream = response.getOutputStream()){
            byte[] buffer = new byte[1024];
            int read = -1;
            while ((read = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, read);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @PostMapping("import")
    public AjaxVo<CalcFormsExcelVo> importExcel(MultipartFile file){
        return ResponseUtil.success(calculatorService.parseExcel(file));
    }
}
