package com.xmu.biomass.sys.user.ro;

import lombok.Data;

/**
 * @ Author：ythu
 * @ Date：2025/4/21  下午4:40
 */
@Data
public class AddUserRo {
    private String username;
    private String password;
    private String mobile;
    private String nickname;
    private String realName;
    private String avatar;
    private String email;
}
