package com.xmu.biomass.sys.user.entity;

import com.xmu.biomass.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SysUser extends BaseEntity {
    private String username;
    private String password;
    private String salt;
    private String mobile;
    private String nickname;
    private String realName;
    private String avatar;
    private String email;
    private Integer lockFlag;
}
