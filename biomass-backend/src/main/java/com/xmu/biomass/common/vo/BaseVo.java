package com.xmu.biomass.common.vo;

import lombok.Data;

import java.util.Date;

@Data
public class BaseVo {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新人
     */
    private String updateBy;
    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 删除标志
     */
    private Double deleteAt;
}
