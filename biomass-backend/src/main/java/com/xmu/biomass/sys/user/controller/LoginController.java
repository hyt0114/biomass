package com.xmu.biomass.sys.user.controller;

import com.xmu.biomass.common.anotations.IgnoreAuth;
import com.xmu.biomass.common.exception.BusinessException;
import com.xmu.biomass.common.utils.JwtUtl;
import com.xmu.biomass.common.utils.ResponseUtil;
import com.xmu.biomass.common.vo.AjaxVo;
import com.xmu.biomass.login.ro.LoginRo;
import com.xmu.biomass.sys.user.entity.SysUser;
import com.xmu.biomass.sys.user.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @ Author：ythu
 * @ Date：2025/3/21 下午4:16
 */
@RestController
@RequiredArgsConstructor
public class LoginController {

    private final SysUserService sysUserService;
    private final JwtUtl jwtUtl;

    @RequestMapping("/login")
    @IgnoreAuth
    public AjaxVo<String> login(@RequestBody LoginRo loginRo) {
        SysUser user = sysUserService.findUserByLogin(loginRo.getUsername(),loginRo.getPassword());
        if(Objects.isNull(user)){
           throw new BusinessException("登录失败");
        }
        String token = jwtUtl.generateToken(user.getId().toString());
        return ResponseUtil.success(token);
    }
}
