package com.xmu.biomass.common.vo;

import lombok.Data;

import java.util.List;

/**
 * @ Author：ythu
 * @ Date：2025/4/6  上午8:49
 */
@Data
public class Pagination<T> {
    private long current;
    private long total;
    private long pages;
    private long size;
    private List<T> records;

}
