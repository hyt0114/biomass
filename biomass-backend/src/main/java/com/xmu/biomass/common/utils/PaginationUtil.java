package com.xmu.biomass.common.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmu.biomass.common.vo.Pagination;

import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @ Author：ythu
 * @ Date：2025/4/6  上午8:48
 */
public class PaginationUtil {
    public static <T,R> Pagination<R> convert(Page<T> page, Function<T,R> function){
        Pagination<R> pagination = new Pagination<>();
        pagination.setCurrent(page.getCurrent());
        pagination.setPages(page.getPages());
        pagination.setSize(page.getSize());
        pagination.setTotal(page.getTotal());
        pagination.setRecords(page.getRecords().stream().map(function).collect(Collectors.toList()));
        return pagination;
    }
}
