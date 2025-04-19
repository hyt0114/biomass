package com.xmu.biomass.plant.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmu.biomass.common.context.UserContext;
import com.xmu.biomass.common.ro.PageRo;
import com.xmu.biomass.common.utils.PaginationUtil;
import com.xmu.biomass.common.vo.EnumVo;
import com.xmu.biomass.common.vo.Pagination;
import com.xmu.biomass.plant.calculator.CarbonRatioCalculator;
import com.xmu.biomass.plant.entity.Mangrove;
import com.xmu.biomass.plant.mapper.MangroveMapper;
import com.xmu.biomass.plant.ro.AddMangroveRo;
import com.xmu.biomass.plant.ro.UpdateMangroveRo;
import com.xmu.biomass.plant.vo.MangroveVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @ Author：ythu
 * @ Date：2025/4/6  上午7:45
 */
@Service
@RequiredArgsConstructor
public class MangroveService {
    private final MangroveMapper mapper;
    private final List<CarbonRatioCalculator> calculators;

    public Pagination<MangroveVo> selectPage(PageRo ro) {
        Page<Mangrove> page = new Page<>(ro.getCurrent(),ro.getSize());
        LambdaQueryWrapper<Mangrove> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(Mangrove::getDeleteAt,0);
        mapper.selectPage(page, queryWrapper);
        Map<String,String> map = calculators.stream().collect(Collectors.toMap(CarbonRatioCalculator::getCalcKey, CarbonRatioCalculator::getCalcDescription));
        return PaginationUtil.convert(page,mangrove -> {
            MangroveVo vo = new MangroveVo();
            vo.setId(mangrove.getId());
            vo.setName(mangrove.getName());
            vo.setScientificName(mangrove.getScientificName());
            vo.setDescription(mangrove.getDescription());
            vo.setCarbonContentRatio(mangrove.getCarbonContentRatio());
            vo.setDensity(mangrove.getDensity());
            vo.setFormula(mangrove.getFormula());
            vo.setFormulaDesc(map.get(mangrove.getFormula()));
            return vo;
        });
    }

    public Integer save(AddMangroveRo ro){
        Mangrove mangrove = new Mangrove();
        mangrove.setName(ro.getName());
        mangrove.setScientificName(ro.getScientificName());
        mangrove.setDescription(ro.getDescription());
        mangrove.setDensity(ro.getDensity());
        mangrove.setCarbonContentRatio(ro.getCarbonContentRatio());
        mangrove.setFormula(ro.getFormula());
        mangrove.setCreateBy(UserContext.getUser().getId().toString());
        mangrove.setUpdateBy(UserContext.getUser().getId().toString());
        return mapper.insert(mangrove);
    }

    public Integer update(UpdateMangroveRo ro) {
        Mangrove mangrove = mapper.selectById(ro.getId());
        Objects.requireNonNull(mangrove,"记录不存在");

        mangrove.setName(ro.getName());
        mangrove.setScientificName(ro.getScientificName());
        mangrove.setDescription(ro.getDescription());
        mangrove.setDensity(ro.getDensity());
        mangrove.setCarbonContentRatio(ro.getCarbonContentRatio());
        mangrove.setFormula(ro.getFormula());
        mangrove.setUpdateBy(UserContext.getUser().getId().toString());
        return mapper.updateById(mangrove);
    }


    public List<MangroveVo> selectList() {
        LambdaQueryWrapper<Mangrove> queryWrapper  = Wrappers.lambdaQuery();
        queryWrapper.eq(Mangrove::getDeleteAt,0);
        List<Mangrove> list = mapper.selectList(queryWrapper);
        return list.stream().map(mangrove->{
           MangroveVo vo = new MangroveVo();
           vo.setId(mangrove.getId());
           vo.setName(mangrove.getName());
           vo.setScientificName(mangrove.getScientificName());
           vo.setDescription(mangrove.getDescription());
           vo.setCarbonContentRatio(mangrove.getCarbonContentRatio());
           vo.setDensity(mangrove.getDensity());
           vo.setFormula(mangrove.getFormula());
           return vo;
       }).toList();

    }

    public Mangrove selectById(String id) {
        return mapper.selectById(id);
    }

    public List<Mangrove> selectByIds(List<Integer> ids) {
        return mapper.selectByIds(ids);
    }

    public List<EnumVo> getPresetCalculators() {
        return calculators.stream().sorted(Comparator.comparingInt(CarbonRatioCalculator::getOrder)).map(c->{
            EnumVo vo = new EnumVo();
            vo.setKey(c.getCalcKey());
            vo.setValue(c.getCalcDescription());
            return vo;
        }).toList();
    }

    public MangroveVo findById(Integer id) {
        Mangrove mangrove = mapper.selectById(id);
        MangroveVo vo = new MangroveVo();
        vo.setId(mangrove.getId());
        vo.setName(mangrove.getName());
        vo.setScientificName(mangrove.getScientificName());
        vo.setDescription(mangrove.getDescription());
        vo.setCarbonContentRatio(mangrove.getCarbonContentRatio());
        vo.setDensity(mangrove.getDensity());
        vo.setFormula(mangrove.getFormula());
        return vo;
    }

    public Integer delete(Integer id) {
        Mangrove mangrove = mapper.selectById(id);
        Objects.requireNonNull(mangrove,"数据不存在");
        mangrove.setDeleteAt(1D);
        return mapper.updateById(mangrove);
    }
}
