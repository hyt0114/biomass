package com.xmu.biomass.sys.user.vo;

import com.xmu.biomass.common.vo.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SysUserVo extends BaseVo {
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
