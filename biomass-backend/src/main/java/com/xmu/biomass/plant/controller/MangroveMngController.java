package com.xmu.biomass.plant.controller;

import com.xmu.biomass.common.ro.PageRo;
import com.xmu.biomass.common.utils.ResponseUtil;
import com.xmu.biomass.common.vo.AjaxVo;
import com.xmu.biomass.common.vo.EnumVo;
import com.xmu.biomass.common.vo.Pagination;
import com.xmu.biomass.plant.enums.PlantCategoryEnum;
import com.xmu.biomass.plant.ro.AddMangroveRo;
import com.xmu.biomass.plant.ro.UpdateMangroveRo;
import com.xmu.biomass.plant.service.MangroveService;
import com.xmu.biomass.plant.vo.MangroveVo;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ Author：ythu
 * @ Date：2025/4/6  上午7:13
 */
@RestController
@RequestMapping("/mangrove")
@RequiredArgsConstructor
public class MangroveMngController {

    private final MangroveService service;

    @PostMapping("page")
    public AjaxVo<Pagination<MangroveVo>> page(@RequestBody PageRo ro) {
        Pagination<MangroveVo> page = service.selectPage(ro);
        return ResponseUtil.success(page);
    }
    @GetMapping("trees")
    public AjaxVo<List<MangroveVo>> trees() {
        List<MangroveVo>  list = service.selectList();
        return ResponseUtil.success(list);
    }
    @GetMapping("preset-calculators")
    public AjaxVo<List<EnumVo>> getPresetCalculators() {
        return ResponseUtil.success(service.getPresetCalculators());
    }
    @GetMapping("/{id}")
    public AjaxVo<MangroveVo> addMangrove(@PathVariable Integer id) {
        return ResponseUtil.success(service.findById(id));
    }
    @PostMapping("add")
    public AjaxVo<Integer> addMangrove( AddMangroveRo ro) {
        Integer id = service.save(ro);
        return ResponseUtil.success(id);
    }
    @PutMapping("update")
    public AjaxVo<Integer> updateMangrove(@ModelAttribute UpdateMangroveRo ro) {
        Integer id = service.update(ro);
        return ResponseUtil.success(id);
    }
    @DeleteMapping("/{id}")
    public AjaxVo<Integer> deleteMangrove(@PathVariable Integer id) {
        return ResponseUtil.success(service.delete(id));
    }
    @GetMapping("categories")
    public AjaxVo<List<EnumVo>> getCategories() {
        List<EnumVo> list = Arrays.stream(PlantCategoryEnum.values()).map(x->{
            EnumVo vo = new EnumVo();
            vo.setKey(x.getCode());
            vo.setValue(x.getDescription());
            return vo;
        }).toList();
        return ResponseUtil.success(list);
    }
}
