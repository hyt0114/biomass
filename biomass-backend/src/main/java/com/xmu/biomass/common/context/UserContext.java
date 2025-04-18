package com.xmu.biomass.common.context;

import com.xmu.biomass.sys.user.entity.SysUser;

/**
 * @ Author：ythu
 * @ Date：2025/3/26  下午3:33
 */
public class UserContext {
    private static ThreadLocal<SysUser> user = new ThreadLocal<>();

    public static SysUser getUser() {
        return user.get();
    }
    public static void setUser(SysUser sysUser) {
        user.set(sysUser);
    }
}
