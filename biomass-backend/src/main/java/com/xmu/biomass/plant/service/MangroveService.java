package com.xmu.biomass.plant.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmu.biomass.common.context.UserContext;
import com.xmu.biomass.common.ro.PageRo;
import com.xmu.biomass.common.utils.PaginationUtil;
import com.xmu.biomass.common.vo.Pagination;
import com.xmu.biomass.plant.entity.Mangrove;
import com.xmu.biomass.plant.mapper.MangroveMapper;
import com.xmu.biomass.plant.ro.AddMangroveRo;
import com.xmu.biomass.plant.ro.UpdateMangroveRo;
import com.xmu.biomass.plant.vo.MangroveVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @ Author：ythu
 * @ Date：2025/4/6  上午7:45
 */
@Service
@RequiredArgsConstructor
public class MangroveService {
    private final MangroveMapper mapper;

    public Pagination<MangroveVo> selectPage(PageRo ro) {
        Page<Mangrove> page = new Page<>(ro.getCurrent(),ro.getSize());
        mapper.selectPage(page, null);
        return PaginationUtil.convert(page,mangrove -> {
            MangroveVo vo = new MangroveVo();
            vo.setId(mangrove.getId());
            vo.setName(mangrove.getName());
            vo.setScientificName(mangrove.getScientificName());
            vo.setDescription(mangrove.getDescription());
            vo.setCarbonContentRatio(mangrove.getCarbonContentRatio());
            vo.setDensity(mangrove.getDensity());
            vo.setCalcKey(mangrove.getCalcKey());
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
        mangrove.setCalcKey(ro.getCalcKey());
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
        mangrove.setCalcKey(ro.getCalcKey());
        mangrove.setUpdateBy(UserContext.getUser().getId().toString());
        return mapper.updateById(mangrove);
    }


    public List<MangroveVo> selectList() {
       List<Mangrove> list = mapper.selectList(new QueryWrapper<>());
       return list.stream().map(mangrove->{
           MangroveVo vo = new MangroveVo();
           vo.setId(mangrove.getId());
           vo.setName(mangrove.getName());
           vo.setScientificName(mangrove.getScientificName());
           vo.setDescription(mangrove.getDescription());
           vo.setCarbonContentRatio(mangrove.getCarbonContentRatio());
           vo.setDensity(mangrove.getDensity());
           vo.setCalcKey(mangrove.getCalcKey());
           return vo;
       }).toList();

    }

    public Mangrove selectByCalcKey(String calcKey) {
        LambdaQueryWrapper<Mangrove> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Mangrove::getCalcKey,calcKey);
        return mapper.selectOne(queryWrapper);

    }

    public Mangrove selectById(String id) {
        return mapper.selectById(id);
    }

    public List<Mangrove> selectByIds(List<Integer> ids) {
        return mapper.selectByIds(ids);
    }
}
